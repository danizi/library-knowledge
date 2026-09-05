package com.example.emptyactivity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.emptyactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val vm: HostViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("HOST", "MainActivity onCreate hash=${System.identityHashCode(this)}")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvHost.text =
            "宿主 · MainActivity #${System.identityHashCode(this)}"

        binding.btnHome.setOnClickListener {
            show(HomeFragment(), "Home")
        }
        binding.btnDetail.setOnClickListener {
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
