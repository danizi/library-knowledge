package com.example.nfc6write

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.nfc.tech.NdefFormatable
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
import com.example.nfc6write.ui.theme.EmptyActivityTheme
import java.util.concurrent.Executors

class MainActivity : ComponentActivity() {
    private val status = mutableStateOf("还没贴到标签")
    private var adapter: NfcAdapter? = null
    private lateinit var pendingIntent: PendingIntent
    private val io = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        adapter = NfcAdapter.getDefaultAdapter(this)
        val launch = Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        pendingIntent = PendingIntent.getActivity(this, 0, launch, PendingIntent.FLAG_MUTABLE)
        setContent {
            EmptyActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Text(text = status.value, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        adapter?.enableForegroundDispatch(this, pendingIntent, null, null)
    }

    override fun onPause() {
        adapter?.disableForegroundDispatch(this)
        super.onPause()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        @Suppress("DEPRECATION")
        val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
        if (tag == null) {
            status.value = "还没贴到标签"
            return
        }
        status.value = "正在写入"
        io.execute { write(tag) }
    }

    private fun write(tag: Tag) {
        val message = NdefMessage(arrayOf(NdefRecord.createTextRecord("en", "nfc-d6")))
        val ndef = Ndef.get(tag)
        try {
            if (ndef != null) {
                ndef.connect()
                ndef.writeNdefMessage(message)
                val back = ndef.ndefMessage
                ndef.close()
                post(back?.records?.firstOrNull()?.let { "写完并读回，记录数=${back.records.size}" } ?: "写完，读回为空")
            } else {
                val formatable = NdefFormatable.get(tag)
                if (formatable == null) {
                    post("这张标签不能写成 NDEF")
                    return
                }
                formatable.connect()
                formatable.format(message)
                formatable.close()
                post("已 format 成一条文本记录")
            }
        } catch (error: Exception) {
            post("写入失败：${error.message}")
        }
    }

    private fun post(text: String) {
        runOnUiThread { status.value = text }
    }
}
