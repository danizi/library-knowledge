package com.example.btd2permission

import android.Manifest
import android.content.pm.PackageManager
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
import androidx.core.content.ContextCompat
import com.example.btd2permission.ui.theme.EmptyActivityTheme

class MainActivity : ComponentActivity() {
    private val status = mutableStateOf("还没请求")

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions(),
    ) { result ->
        val scan = result[Manifest.permission.BLUETOOTH_SCAN] == true
        val connect = result[Manifest.permission.BLUETOOTH_CONNECT] == true
        status.value = "BLUETOOTH_SCAN=$scan\nBLUETOOTH_CONNECT=$connect"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        showCurrent()
        setContent {
            EmptyActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Text(status.value)
                        Button(onClick = {
                            permissionLauncher.launch(
                                arrayOf(
                                    Manifest.permission.BLUETOOTH_SCAN,
                                    Manifest.permission.BLUETOOTH_CONNECT,
                                ),
                            )
                        }) { Text("请求扫描和连接") }
                    }
                }
            }
        }
    }

    private fun showCurrent() {
        val scan = granted(Manifest.permission.BLUETOOTH_SCAN)
        val connect = granted(Manifest.permission.BLUETOOTH_CONNECT)
        status.value = "当前 BLUETOOTH_SCAN=$scan\n当前 BLUETOOTH_CONNECT=$connect"
    }

    private fun granted(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    }
}
