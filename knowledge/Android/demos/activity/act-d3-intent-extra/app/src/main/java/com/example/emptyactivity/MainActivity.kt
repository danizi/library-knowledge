package com.example.emptyactivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.emptyactivity.ui.theme.EmptyActivityTheme

const val EXTRA_NAME = "name"
const val EXTRA_REPLY = "reply"
const val EXTRA_PING = "ping"

class MainActivity : ComponentActivity() {
    private var reply by mutableStateOf("（还没有回传）")
    private var ping by mutableIntStateOf(0)

    private val secondLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            reply = result.data?.getStringExtra(EXTRA_REPLY) ?: "（空回传）"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("INTENT", "MainActivity onCreate")
        enableEdgeToEdge()
        setContent {
            EmptyActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        reply = reply,
                        ping = ping,
                        onOpenSecond = {
                            secondLauncher.launch(
                                Intent(this, SecondActivity::class.java)
                                    .putExtra(EXTRA_NAME, "xiaomin")
                            )
                        },
                        onOpenSelf = {
                            startActivity(
                                Intent(this, MainActivity::class.java)
                                    .putExtra(EXTRA_PING, ping + 1)
                            )
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        ping = intent.getIntExtra(EXTRA_PING, 0)
        Log.d("INTENT", "MainActivity onNewIntent ping=$ping（实例复用，没有 onCreate）")
    }
}

@Composable
fun MainScreen(
    reply: String,
    ping: Int,
    onOpenSecond: () -> Unit,
    onOpenSelf: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "第一屏 · MainActivity")
        Text(text = "回传：$reply", modifier = Modifier.padding(top = 12.dp))
        Text(text = "onNewIntent ping=$ping", modifier = Modifier.padding(top = 8.dp))
        Button(
            onClick = onOpenSecond,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("打开第二屏（带 Extra）")
        }
        Button(
            onClick = onOpenSelf,
            modifier = Modifier.padding(top = 12.dp)
        ) {
            Text("再开一次自己（singleTop）")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    EmptyActivityTheme {
        MainScreen(
            reply = "（还没有回传）",
            ping = 0,
            onOpenSecond = {},
            onOpenSelf = {}
        )
    }
}
