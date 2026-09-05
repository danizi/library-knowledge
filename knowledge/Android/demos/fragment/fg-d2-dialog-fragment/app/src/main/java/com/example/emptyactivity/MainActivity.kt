package com.example.emptyactivity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
            goHome()
        }
        binding.btnDetail.setOnClickListener {
            showDetail()
        }
        binding.btnDialog.setOnClickListener {
            showConfirm()
        }

        if (savedInstanceState == null) {
            // 根隔断不入栈：返回到这里再按一次才退出 App
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.fragment_container, HomeFragment(), "Home")
            }
            vm.markSwitch("Home")
        }
    }

    override fun onDestroy() {
        Log.d("HOST", "MainActivity onDestroy hash=${System.identityHashCode(this)}")
        super.onDestroy()
    }

    private fun goHome() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            vm.markSwitch("Home")
            return
        }
        val current = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (current is HomeFragment) return
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container, HomeFragment(), "Home")
        }
        vm.markSwitch("Home")
    }

    private fun showDetail() {
        val current = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (current is DetailFragment) return
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container, DetailFragment(), "Detail")
            addToBackStack("Detail")
        }
        vm.markSwitch("Detail")
        Log.d("HOST", "push Detail")
    }

    private fun showConfirm() {
        if (supportFragmentManager.findFragmentByTag("confirm") != null) return
        ConfirmDialogFragment().show(supportFragmentManager, "confirm")
        Log.d("HOST", "show ConfirmDialogFragment")
    }
}
