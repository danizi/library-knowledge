package com.example.nfc1adapter

import android.nfc.NfcAdapter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.nfc1adapter.ui.theme.EmptyActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val adapter = NfcAdapter.getDefaultAdapter(this)
        val text = when {
            adapter == null -> "getDefaultAdapter() 为 null：这台设备没有 NFC。模拟器经常是这样，请换真机。"
            adapter.isEnabled -> "适配器存在，isEnabled() 为 true。"
            else -> "适配器存在，isEnabled() 为 false。请到系统设置里打开，本页不改开关。"
        }
        setContent {
            EmptyActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Text(text = text, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
