package com.example.emptyactivity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment

class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("HOST", "HomeFragment onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("HOST", "HomeFragment onViewCreated")
    }

    override fun onResume() {
        super.onResume()
        Log.d("HOST", "HomeFragment onResume")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.d("HOST", "HomeFragment onHiddenChanged hidden=$hidden")
    }

    override fun onDestroyView() {
        Log.d("HOST", "HomeFragment onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("HOST", "HomeFragment onDestroy")
        super.onDestroy()
    }
}
