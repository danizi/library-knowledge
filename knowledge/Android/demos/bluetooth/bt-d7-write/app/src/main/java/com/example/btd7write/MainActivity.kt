package com.example.btd7write

import android.Manifest
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.bluetooth.BluetoothStatusCodes
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.example.btd7write.ui.theme.EmptyActivityTheme

class MainActivity : ComponentActivity() {
    private val address = mutableStateOf("")
    private val status = mutableStateOf("只写一个字节，等 onCharacteristicWrite。")
    private var gatt: BluetoothGatt? = null
    private var wrote = false

    private val callback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt, statusCode: Int, newState: Int) {
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                gatt.discoverServices()
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                post("STATE_DISCONNECTED")
            }
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt, statusCode: Int) {
            if (statusCode != BluetoothGatt.GATT_SUCCESS || wrote) return
            val target = gatt.services.flatMap { it.characteristics }.firstOrNull { characteristic ->
                val props = characteristic.properties
                props and BluetoothGattCharacteristic.PROPERTY_WRITE != 0 ||
                    props and BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE != 0
            }
            if (target == null) {
                post("没有可写特征")
                return
            }
            val writeType = if (target.properties and BluetoothGattCharacteristic.PROPERTY_WRITE != 0) {
                BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT
            } else {
                BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE
            }
            wrote = true
            val payload = byteArrayOf(0x01)
            val submitted = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                gatt.writeCharacteristic(target, payload, writeType)
            } else {
                @Suppress("DEPRECATION")
                run {
                    target.writeType = writeType
                    target.value = payload
                    if (gatt.writeCharacteristic(target)) BluetoothStatusCodes.SUCCESS else BluetoothStatusCodes.ERROR_UNKNOWN
                }
            }
            post("写入请求已提交：$submitted，等待 onCharacteristicWrite")
        }

        override fun onCharacteristicWrite(
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic,
            statusCode: Int,
        ) {
            post(
                if (statusCode == BluetoothGatt.GATT_SUCCESS) {
                    "GATT_SUCCESS，写完 ${characteristic.uuid}"
                } else {
                    "写入失败：$statusCode"
                },
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmptyActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Text(status.value)
                        BasicTextField(value = address.value, onValueChange = { address.value = it })
                        Button(onClick = { connectAndWriteOnce() }) { Text("连接并写一个字节") }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        gatt?.close()
        gatt = null
        super.onDestroy()
    }

    private fun connectAndWriteOnce() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT)
            != PackageManager.PERMISSION_GRANTED
        ) {
            status.value = "没有 BLUETOOTH_CONNECT。先跑 bt-d2-permission。"
            return
        }
        val adapter = getSystemService(BluetoothManager::class.java)?.adapter
        if (adapter == null || !adapter.isEnabled) {
            status.value = "没有适配器，或蓝牙没开"
            return
        }
        gatt?.close()
        wrote = false
        val device = try {
            adapter.getRemoteDevice(address.value.trim())
        } catch (error: IllegalArgumentException) {
            status.value = "地址不合法：${error.message}"
            return
        }
        gatt = device.connectGatt(this, false, callback)
    }

    private fun post(message: String) {
        runOnUiThread { status.value = message }
    }
}
