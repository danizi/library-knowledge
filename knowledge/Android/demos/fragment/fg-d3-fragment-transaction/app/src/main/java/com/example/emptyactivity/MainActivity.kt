package com.example.emptyactivity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import com.example.emptyactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    /** replace 在 Home / Detail 之间来回切 */
    private var replaceToDetail = true
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("HOST", "MainActivity onCreate hash=${System.identityHashCode(this)}")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvHost.text =
            "宿主 · MainActivity #${System.identityHashCode(this)}"

        binding.btnReplaceCommit.setOnClickListener {
            doReplace(now = false)
        }
        binding.btnReplaceNow.setOnClickListener {
            doReplace(now = true)
        }
        binding.btnHideShow.setOnClickListener {
            doHideShow()
        }
        binding.btnAdd.setOnClickListener {
            doAdd()
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

    private fun doReplace(now: Boolean) {
        val next: Fragment
        val tag: String
        if (replaceToDetail) {
            next = DetailFragment()
            tag = "Detail"
        } else {
            next = HomeFragment()
            tag = "Home"
        }
        replaceToDetail = !replaceToDetail
        val how = if (now) "commitNow" else "commit"
        Log.d("HOST", "=== REPLACE → $tag ($how) ===")
        if (now) {
            supportFragmentManager.commitNow {
                setReorderingAllowed(true)
                replace(R.id.fragment_container, next, tag)
            }
        } else {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.fragment_container, next, tag)
            }
        }
        logSnapshot("立刻 find（$how 之后）")
    }

    private fun doHideShow() {
        val fm = supportFragmentManager
        val extra = fm.findFragmentByTag("Extra")
        val home = fm.findFragmentByTag("Home")
        val detail = fm.findFragmentByTag("Detail")

        Log.d("HOST", "=== HIDE/SHOW ===")
        if (home != null && detail != null) {
            fm.commit {
                setReorderingAllowed(true)
                extra?.let { remove(it) }
                if (home.isHidden) {
                    show(home)
                    hide(detail)
                    Log.d("HOST", "show Home / hide Detail")
                } else {
                    hide(home)
                    show(detail)
                    Log.d("HOST", "hide Home / show Detail")
                }
            }
            return
        }

        val newHome = home ?: HomeFragment()
        val newDetail = detail ?: DetailFragment()
        fm.commit {
            setReorderingAllowed(true)
            extra?.let { remove(it) }
            if (home == null) add(R.id.fragment_container, newHome, "Home")
            if (detail == null) add(R.id.fragment_container, newDetail, "Detail")
            show(newHome)
            hide(newDetail)
            Log.d("HOST", "首次：两个都 add，show Home / hide Detail（Home 不应 onDestroy）")
        }
    }

    private fun doAdd() {
        val fm = supportFragmentManager
        if (fm.findFragmentByTag("Extra") != null) {
            Log.d("HOST", "=== ADD 跳过：Extra 已在 ===")
            return
        }
        Log.d("HOST", "=== ADD Extra（底下不应 onDestroy）===")
        fm.commit {
            setReorderingAllowed(true)
            add(R.id.fragment_container, ExtraFragment(), "Extra")
        }
        logSnapshot("立刻 find（commit 之后）")
    }

    private fun logSnapshot(prefix: String) {
        val fm = supportFragmentManager
        val top = fm.findFragmentById(R.id.fragment_container)?.javaClass?.simpleName ?: "null"
        val alive = fm.fragments.joinToString { f ->
            val h = if (f.isHidden) "(hidden)" else ""
            "${f.javaClass.simpleName}$h"
        }
        Log.d("HOST", "$prefix top=$top alive=[$alive]")
    }
}
