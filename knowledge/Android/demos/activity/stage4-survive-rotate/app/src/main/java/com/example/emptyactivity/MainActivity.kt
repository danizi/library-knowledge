package com.example.emptyactivity

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.emptyactivity.ui.theme.EmptyActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ROTATE", "MainActivity onCreate hash=${System.identityHashCode(this)}")
        enableEdgeToEdge()
        setContent {
            EmptyActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CounterScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CounterScreen(
    modifier: Modifier = Modifier,
    vm: CounterViewModel = viewModel()
) {
    var rememberCount by remember { mutableIntStateOf(0) }
    val activityHash = System.identityHashCode(LocalContext.current)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("旋转对照：ViewModel 还在，remember 归零")
        Text(
            text = "Activity 实例 #$activityHash",
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "ViewModel count = ${vm.count}",
            modifier = Modifier.padding(top = 16.dp)
        )
        Button(
            onClick = { vm.increment() },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("ViewModel +1")
        }
        Text(
            text = "remember count = $rememberCount",
            modifier = Modifier.padding(top = 24.dp)
        )
        Button(
            onClick = { rememberCount++ },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("remember +1")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterScreenPreview() {
    EmptyActivityTheme {
        CounterScreen(vm = CounterViewModel())
    }
}
