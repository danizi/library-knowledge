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

        binding.tvHost.text = "宿主 · MainActivity #${System.identityHashCode(this)}"

        binding.btnHideShow.setOnClickListener { doHideShow() }
        binding.btnReplaceStack.setOnClickListener { pushDetail() }
        binding.btnPost.setOnClickListener {
            Log.d("HOST", "=== 宿主发信箱 ===")
            vm.post()
        }
        binding.btnDanger.setOnClickListener {
            val home = supportFragmentManager.findFragmentByTag("Home") as? HomeFragment
            if (home == null) {
                Log.d("HOST", "没有 HomeFragment（tag=Home）")
                return@setOnClickListener
            }
            home.registerDangerousObserver()
        }
        binding.btnPop.setOnClickListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                Log.d("HOST", "=== popBackStack ===")
                supportFragmentManager.popBackStack()
            } else {
                Log.d("HOST", "回退栈空")
            }
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

    private fun doHideShow() {
        val fm = supportFragmentManager
        val home = fm.findFragmentByTag("Home")
        val detail = fm.findFragmentByTag("Detail")

        Log.d("HOST", "=== HIDE/SHOW ===")
        if (home != null && detail != null && fm.backStackEntryCount == 0) {
            fm.commit {
                setReorderingAllowed(true)
                if (home.isHidden) {
                    show(home)
                    hide(detail)
                    Log.d("HOST", "show Home / hide Detail（应只有 onHiddenChanged）")
                } else {
                    hide(home)
                    show(detail)
                    Log.d("HOST", "hide Home / show Detail（应只有 onHiddenChanged）")
                }
            }
            return
        }

        if (fm.backStackEntryCount > 0) {
            Log.d("HOST", "当前在入栈模式，先 pop 再 hide/show")
            return
        }

        val newHome = home ?: HomeFragment()
        val newDetail = detail ?: DetailFragment()
        fm.commit {
            setReorderingAllowed(true)
            if (home == null) add(R.id.fragment_container, newHome, "Home")
            if (detail == null) add(R.id.fragment_container, newDetail, "Detail")
            show(newHome)
            hide(newDetail)
            Log.d("HOST", "首次：两个 add，show Home / hide Detail")
        }
    }

    private fun pushDetail() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            Log.d("HOST", "已有入栈记录，先 pop 或再点一次入栈跳过")
            return
        }
        val home = supportFragmentManager.findFragmentByTag("Home")
        val detail = supportFragmentManager.findFragmentByTag("Detail")
        Log.d("HOST", "=== REPLACE Detail + addToBackStack（Home 应 onDestroyView，不应 onDestroy）===")
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            detail?.let { if (it !== home) remove(it) }
            replace(R.id.fragment_container, DetailFragment(), "Detail")
            addToBackStack("Detail")
        }
    }
}
