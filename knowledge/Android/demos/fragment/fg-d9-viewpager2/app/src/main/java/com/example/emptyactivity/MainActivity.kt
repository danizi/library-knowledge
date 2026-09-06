package com.example.emptyactivity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
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

        binding.pager.adapter = DemoPagerAdapter(this)
        binding.pager.offscreenPageLimit = 1
        binding.pager.post {
            (binding.pager.getChildAt(0) as? RecyclerView)?.setItemViewCacheSize(0)
        }

        binding.btnPage0.setOnClickListener {
            Log.d("HOST", "=== jump to Page0 ===")
            binding.pager.setCurrentItem(0, false)
        }
        binding.btnPage2.setOnClickListener {
            Log.d("HOST", "=== jump to Page2 ===")
            binding.pager.setCurrentItem(2, false)
        }

        Log.d("HOST", "ViewPager2 + FragmentStateAdapter · 共 3 页 · offscreenLimit=1")
    }

    override fun onDestroy() {
        Log.d("HOST", "MainActivity onDestroy hash=${System.identityHashCode(this)}")
        super.onDestroy()
    }
}
