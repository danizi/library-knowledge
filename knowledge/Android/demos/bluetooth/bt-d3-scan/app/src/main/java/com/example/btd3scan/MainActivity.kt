package com.example.btd3scan

import android.Manifest
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.example.btd3scan.ui.theme.EmptyActivityTheme

class MainActivity : ComponentActivity() {
    private val status = mutableStateOf("还没扫描")
    private val addresses = mutableStateListOf<String>()
    private val handler = Handler(Looper.getMainLooper())
    private var scanning = false

    private val callback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            val address = result.device.address
            runOnUiThread {
                if (address !in addresses) addresses.add(address)
            }
        }
    }

    private val stopScan = Runnable {
        stop()
        status.value = "10 秒到，已 stopScan。看到 ${addresses.size} 个地址"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmptyActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Text(status.value)
                        Button(onClick = { startLimitedScan() }) { Text("扫描 10 秒") }
                        addresses.forEach { Text(it) }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        handler.removeCallbacks(stopScan)
        stop()
        super.onDestroy()
    }

    private fun startLimitedScan() {
        if (scanning) return
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN)
            != PackageManager.PERMISSION_GRANTED
        ) {
            status.value = "没有 BLUETOOTH_SCAN。先跑 bt-d2-permission。"
            return
        }
        val manager = getSystemService(BluetoothManager::class.java)
        val adapter = manager?.adapter
        if (adapter == null) {
            status.value = "getAdapter() 为 null"
            return
        }
        val scanner = try {
            if (!adapter.isEnabled) {
                status.value = "蓝牙没开，不调用 startScan"
                return
            }
            adapter.bluetoothLeScanner
        } catch (error: SecurityException) {
            status.value = "系统拒绝：${error.message}"
            return
        }
        if (scanner == null) {
            status.value = "getBluetoothLeScanner() 为 null"
            return
        }
        addresses.clear()
        scanning = true
        status.value = "扫描中，10 秒后停止"
        scanner.startScan(callback)
        handler.postDelayed(stopScan, 10_000)
    }

    private fun stop() {
        if (!scanning) return
        scanning = false
        val scanner = getSystemService(BluetoothManager::class.java)?.adapter?.bluetoothLeScanner
        try {
            scanner?.stopScan(callback)
        } catch (_: SecurityException) {
            status.value = "停止扫描时被系统拒绝"
        }
    }
}
