package com.example.emptyactivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
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

class MainActivity : ComponentActivity() {
    private fun life(event: String) = Log.d("LIFE", "${javaClass.simpleName} $event")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        life("onCreate")
        enableEdgeToEdge()
        setContent {
            EmptyActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        onOpenSecond = {
                            startActivity(Intent(this, SecondActivity::class.java))
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        life("onStart")
    }

    override fun onResume() {
        super.onResume()
        life("onResume")
    }

    override fun onPause() {
        super.onPause()
        life("onPause")
    }

    override fun onStop() {
        super.onStop()
        life("onStop")
    }

    override fun onRestart() {
        super.onRestart()
        life("onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        life("onDestroy")
    }
}

@Composable
fun MainScreen(onOpenSecond: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "第一屏 · MainActivity")
        Button(
            onClick = onOpenSecond,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("打开第二屏")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    EmptyActivityTheme {
        MainScreen(onOpenSecond = {})
    }
}
