package com.example.nfc4read

import android.content.Intent
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
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
import com.example.nfc4read.ui.theme.EmptyActivityTheme

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
        val raw = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
        val record = raw?.firstOrNull()
            ?.let { it as? android.nfc.NdefMessage }
            ?.records
            ?.firstOrNull()
        status.value = if (record == null) {
            "还没贴到标签"
        } else if (
            record.tnf == NdefRecord.TNF_WELL_KNOWN &&
            record.type.contentEquals(NdefRecord.RTD_TEXT)
        ) {
            textOf(record.payload)
        } else {
            "第一条记录 TNF=${record.tnf}，不是文本记录"
        }
    }

    private fun textOf(payload: ByteArray): String {
        if (payload.isEmpty()) return "文本记录是空的"
        val langLength = payload[0].toInt() and 0x3F
        val start = 1 + langLength
        if (start > payload.size) return "语言码长度超出载荷"
        return payload.copyOfRange(start, payload.size).toString(Charsets.UTF_8)
    }
}
