package com.example.emptyactivity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.emptyactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("HOST", "MainActivity onCreate hash=${System.identityHashCode(this)}")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvHost.text =
            "宿主 · MainActivity #${System.identityHashCode(this)}"

        if (savedInstanceState == null) {
            val parent = ParentFragment()
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.fragment_container, parent, "Parent")
                // 系统返回先问这本「主导航」里的子栈
                setPrimaryNavigationFragment(parent)
            }
            Log.d("HOST", "Activity 装 Parent（supportFragmentManager + primaryNavigation）")
        }
    }

    override fun onDestroy() {
        Log.d("HOST", "MainActivity onDestroy hash=${System.identityHashCode(this)}")
        super.onDestroy()
    }
}
