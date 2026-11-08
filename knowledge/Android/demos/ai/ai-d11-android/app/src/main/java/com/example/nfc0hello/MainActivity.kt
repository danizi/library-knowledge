package com.example.nfc0hello

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.nfc0hello.ui.theme.EmptyActivityTheme
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmptyActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AskScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AskScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var answer by remember { mutableStateOf("还没问") }
    var filename by remember { mutableStateOf("") }
    var elapsed by remember { mutableStateOf("") }
    Column(modifier) {
        Button(onClick = {
            Thread {
                val result = postAsk("冷启动是什么？")
                (context as Activity).runOnUiThread {
                    answer = result.first
                    filename = result.second
                    elapsed = result.third
                }
            }.start()
        }) {
            Text("提问")
        }
        Text(answer)
        Text(filename)
        Text(elapsed)
    }
}

private fun postAsk(question: String): Triple<String, String, String> {
    val connection = URL("http://10.0.2.2:8000/ask").openConnection() as HttpURLConnection
    connection.requestMethod = "POST"
    connection.setRequestProperty("Content-Type", "application/json")
    connection.doOutput = true
    connection.outputStream.use { stream ->
        stream.write("""{"text":"$question"}""".toByteArray())
    }
    val json = JSONObject(connection.inputStream.bufferedReader().readText())
    return Triple(
        json.optString("answer"),
        json.optString("filename"),
        json.optInt("elapsed_ms").toString(),
    )
}
