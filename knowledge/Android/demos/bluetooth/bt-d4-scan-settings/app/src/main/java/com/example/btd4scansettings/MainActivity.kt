package com.example.btd4scansettings

import android.Manifest
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.example.btd4scansettings.ui.theme.EmptyActivityTheme

class MainActivity : ComponentActivity() {
    private val nameFilter = mutableStateOf("")
    private val status = mutableStateOf("低延迟扫描。名字留空表示不过滤。")
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

        override fun onScanFailed(errorCode: Int) {
            runOnUiThread { status.value = "onScanFailed：$errorCode" }
        }
    }

    private val stopScan = Runnable {
        stop()
        status.value = "10 秒到，已 stopScan。SCAN_MODE_LOW_LATENCY，看到 ${addresses.size} 个地址"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmptyActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Text(status.value)
                        BasicTextField(value = nameFilter.value, onValueChange = { nameFilter.value = it })
                        Button(onClick = { startFilteredScan() }) { Text("按设置扫描 10 秒") }
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

    private fun startFilteredScan() {
        if (scanning) return
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN)
            != PackageManager.PERMISSION_GRANTED
        ) {
            status.value = "没有 BLUETOOTH_SCAN。先跑 bt-d2-permission。"
            return
        }
        val adapter = getSystemService(BluetoothManager::class.java)?.adapter
        if (adapter == null || !adapter.isEnabled) {
            status.value = "没有适配器，或蓝牙没开"
            return
        }
        val scanner = adapter.bluetoothLeScanner
        if (scanner == null) {
            status.value = "getBluetoothLeScanner() 为 null"
            return
        }
        val settings = ScanSettings.Builder()
            .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
            .build()
        val filters = mutableListOf<ScanFilter>()
        val typed = nameFilter.value.trim()
        if (typed.isNotEmpty()) {
            filters += ScanFilter.Builder().setDeviceName(typed).build()
        }
        addresses.clear()
        scanning = true
        status.value = if (typed.isEmpty()) "低延迟，无名称过滤" else "低延迟，名称过滤：$typed"
        scanner.startScan(filters, settings, callback)
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
