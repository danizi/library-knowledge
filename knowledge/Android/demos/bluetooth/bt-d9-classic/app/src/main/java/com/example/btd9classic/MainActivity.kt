package com.example.btd9classic

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.btd9classic.ui.theme.EmptyActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val classic = packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)
        val le = packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)
        setContent {
            EmptyActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Text(
                        text = "FEATURE_BLUETOOTH=$classic\nFEATURE_BLUETOOTH_LE=$le\n本页不调用 createRfcommSocketToServiceRecord，也不传文件。",
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}
