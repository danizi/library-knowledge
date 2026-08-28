package com.example.emptyactivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.emptyactivity.ui.theme.EmptyActivityTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra(EXTRA_NAME) ?: "（没有 Extra）"
        enableEdgeToEdge()
        setContent {
            EmptyActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SecondScreen(
                        name = name,
                        onReply = {
                            setResult(
                                RESULT_OK,
                                Intent().putExtra(EXTRA_REPLY, "收到了 $name")
                            )
                            finish()
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SecondScreen(
    name: String,
    onReply: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "第二屏 · Extra = $name")
        Button(
            onClick = onReply,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("回传并关闭")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview() {
    EmptyActivityTheme {
        SecondScreen(name = "xiaomin", onReply = {})
    }
}
