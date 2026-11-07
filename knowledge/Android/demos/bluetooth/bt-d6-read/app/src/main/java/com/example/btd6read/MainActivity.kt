package com.example.btd6read

import android.Manifest
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.content.pm.PackageManager
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
import com.example.btd6read.ui.theme.EmptyActivityTheme

class MainActivity : ComponentActivity() {
    private val address = mutableStateOf("")
    private val status = mutableStateOf("填入 bt-d3 看到的地址，然后连接并读一次")
    private var gatt: BluetoothGatt? = null

    private val callback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt, statusCode: Int, newState: Int) {
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                post("STATE_CONNECTED，discoverServices()")
                gatt.discoverServices()
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                post("STATE_DISCONNECTED")
            }
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt, statusCode: Int) {
            if (statusCode != BluetoothGatt.GATT_SUCCESS) {
                post("发现服务失败：$statusCode")
                return
            }
            val target = gatt.services
                .flatMap { it.characteristics }
                .firstOrNull { it.properties and BluetoothGattCharacteristic.PROPERTY_READ != 0 }
            if (target == null) {
                post("没有带读取属性的特征")
                return
            }
            gatt.readCharacteristic(target)
            post("已请求读取 ${target.uuid}")
        }

        @Suppress("DEPRECATION")
        override fun onCharacteristicRead(
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic,
            statusCode: Int,
        ) {
            if (statusCode != BluetoothGatt.GATT_SUCCESS) {
                post("读取失败：$statusCode")
                return
            }
            val bytes = characteristic.value ?: byteArrayOf()
            val hex = bytes.joinToString(" ") { "%02X".format(it) }
            post("GATT_SUCCESS ${characteristic.uuid}：$hex")
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
                        BasicTextField(
                            value = address.value,
                            onValueChange = { address.value = it },
                        )
                        Button(onClick = { connectAndRead() }) { Text("连接并读一次") }
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

    private fun connectAndRead() {
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
        val device = try {
            adapter.getRemoteDevice(address.value.trim())
        } catch (error: IllegalArgumentException) {
            status.value = "地址不合法：${error.message}"
            return
        }
        status.value = "connectGatt(..., false, ...)"
        gatt = device.connectGatt(this, false, callback)
    }

    private fun post(message: String) {
        runOnUiThread { status.value = message }
    }
}
