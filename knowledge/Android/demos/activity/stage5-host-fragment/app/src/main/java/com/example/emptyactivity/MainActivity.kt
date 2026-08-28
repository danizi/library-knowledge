package com.example.emptyactivity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    private val vm: HostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("HOST", "MainActivity onCreate hash=${System.identityHashCode(this)}")
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.tv_host).text =
            "宿主 · MainActivity #${System.identityHashCode(this)}"

        findViewById<Button>(R.id.btn_home).setOnClickListener {
            show(HomeFragment(), "Home")
        }
        findViewById<Button>(R.id.btn_detail).setOnClickListener {
            show(DetailFragment(), "Detail")
        }

        if (savedInstanceState == null) {
            show(HomeFragment(), "Home")
        }
    }

    override fun onDestroy() {
        Log.d("HOST", "MainActivity onDestroy hash=${System.identityHashCode(this)}")
        super.onDestroy()
    }

    private fun show(fragment: Fragment, page: String) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container, fragment, page)
        }
        vm.markSwitch(page)
    }
}
