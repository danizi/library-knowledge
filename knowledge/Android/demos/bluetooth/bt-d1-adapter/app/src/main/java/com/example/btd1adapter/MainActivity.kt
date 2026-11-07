package com.example.btd1adapter

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.example.btd1adapter.ui.theme.EmptyActivityTheme

class MainActivity : ComponentActivity() {
    private val status = mutableStateOf("还没查询")

    private val enableLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        status.value = if (result.resultCode == RESULT_OK) {
            "RESULT_OK，用户允许打开蓝牙"
        } else {
            "RESULT_CANCELED，用户拒绝或打开失败"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        refresh()
        setContent {
            EmptyActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Text(status.value)
                        Button(onClick = { refresh() }) { Text("再查一次") }
                        Button(onClick = { requestEnable() }) { Text("请求打开蓝牙") }
                    }
                }
            }
        }
    }

    private fun adapter(): BluetoothAdapter? {
        val manager = getSystemService(BluetoothManager::class.java)
        return manager?.adapter
    }

    private fun refresh() {
        val adapter = adapter()
        if (adapter == null) {
            status.value = "getAdapter() 为 null：这台设备没有蓝牙"
            return
        }
        val enabled = try {
            adapter.isEnabled
        } catch (error: SecurityException) {
            status.value = "系统拒绝查询开关：${error.message}。先做完 D2 的 BLUETOOTH_CONNECT 再回来。"
            return
        }
        status.value = if (enabled) "适配器存在，isEnabled() 为 true" else "适配器存在，isEnabled() 为 false"
    }

    private fun requestEnable() {
        val adapter = adapter() ?: run {
            status.value = "没有适配器，不能请求打开"
            return
        }
        val enabled = try {
            adapter.isEnabled
        } catch (error: SecurityException) {
            status.value = "系统拒绝查询开关：${error.message}"
            return
        }
        if (enabled) {
            status.value = "已经开着，不再发 ACTION_REQUEST_ENABLE"
            return
        }
        try {
            enableLauncher.launch(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE))
        } catch (error: SecurityException) {
            status.value = "系统拒绝打开请求：${error.message}。先做完 D2 再回来。"
        }
    }
}
