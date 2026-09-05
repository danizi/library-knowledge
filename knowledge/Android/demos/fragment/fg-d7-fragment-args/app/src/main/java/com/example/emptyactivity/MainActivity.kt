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

        binding.btnOpenDetail.setOnClickListener {
            openDetail()
        }

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.fragment_container, HomeFragment(), "Home")
            }
        }
    }

    override fun onDestroy() {
        Log.d("HOST", "MainActivity onDestroy hash=${System.identityHashCode(this)}")
        super.onDestroy()
    }

    private fun openDetail() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            Log.d("HOST", "已在 Detail，先返回再开")
            return
        }
        // 空构造 + arguments，不要 DetailFragment("42")
        val detail = DetailFragment.newInstance(ITEM_ID)
        Log.d("HOST", "=== open Detail arguments id=$ITEM_ID ===")
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container, detail, "Detail")
            addToBackStack("Detail")
        }
    }

    companion object {
        const val ITEM_ID = "book-42"
    }
}
