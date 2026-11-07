package com.example.btd8notify

import android.Manifest
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattDescriptor
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import java.util.UUID
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
import com.example.btd8notify.ui.theme.EmptyActivityTheme

class MainActivity : ComponentActivity() {
    private val address = mutableStateOf("")
    private val status = mutableStateOf("打开通知。描述符写完之前不再做下一次 GATT 操作。")
    private var gatt: BluetoothGatt? = null
    private var subscribed = false

    private val callback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt, statusCode: Int, newState: Int) {
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                gatt.discoverServices()
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                post("STATE_DISCONNECTED")
            }
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt, statusCode: Int) {
            if (statusCode != BluetoothGatt.GATT_SUCCESS || subscribed) return
            val target = gatt.services.flatMap { it.characteristics }.firstOrNull {
                it.properties and BluetoothGattCharacteristic.PROPERTY_NOTIFY != 0
            }
            if (target == null) {
                post("没有带通知属性的特征")
                return
            }
            val descriptor = target.getDescriptor(CCCD)
            if (descriptor == null) {
                post("没有 Client Characteristic Configuration 描述符")
                return
            }
            subscribed = true
            gatt.setCharacteristicNotification(target, true)
            val enabled = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                gatt.writeDescriptor(descriptor, enabled)
            } else {
                @Suppress("DEPRECATION")
                descriptor.value = enabled
                @Suppress("DEPRECATION")
                gatt.writeDescriptor(descriptor)
            }
            post("已请求写入通知描述符")
        }

        override fun onDescriptorWrite(gatt: BluetoothGatt, descriptor: BluetoothGattDescriptor, statusCode: Int) {
            post(if (statusCode == BluetoothGatt.GATT_SUCCESS) "描述符 GATT_SUCCESS，等待 onCharacteristicChanged" else "描述符写入失败：$statusCode")
        }

        @Suppress("DEPRECATION")
        override fun onCharacteristicChanged(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic) {
            val bytes = characteristic.value ?: byteArrayOf()
            val hex = bytes.joinToString(" ") { "%02X".format(it) }
            post("onCharacteristicChanged ${characteristic.uuid}：$hex")
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
                        Button(onClick = { connectAndNotify() }) { Text("连接并打开通知") }
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

    private fun connectAndNotify() {
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
        subscribed = false
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

    companion object {
        private val CCCD: UUID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb")
    }
}
