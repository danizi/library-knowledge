package com.example.nfc3view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.example.nfc3view.ui.theme.EmptyActivityTheme

class MainActivity : ComponentActivity() {
    private val status = mutableStateOf("还没贴到标签")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        show(intent)
        setContent {
            EmptyActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Text(text = status.value, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        show(intent)
    }

    private fun show(intent: Intent) {
        status.value = if (intent.action == Intent.ACTION_VIEW && intent.dataString != null) {
            "ACTION_VIEW\n${intent.dataString}"
        } else if (intent.action != null && intent.action!!.startsWith("android.nfc")) {
            intent.action!!
        } else {
            "还没贴到标签"
        }
    }
}
