package com.example.emptyactivity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment

class ChildBFragment : Fragment(R.layout.fragment_child_b) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("HOST", "ChildB onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(
            "HOST",
            "ChildB onViewCreated · 父是 ${parentFragment?.javaClass?.simpleName} · 用的是父的 childFM"
        )
    }

    override fun onDestroyView() {
        Log.d("HOST", "ChildB onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("HOST", "ChildB onDestroy")
        super.onDestroy()
    }
}
