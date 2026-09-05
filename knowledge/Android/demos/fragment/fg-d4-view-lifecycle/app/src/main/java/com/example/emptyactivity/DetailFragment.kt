package com.example.emptyactivity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment

class DetailFragment : Fragment(R.layout.fragment_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("HOST", "DetailFragment onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("HOST", "DetailFragment onViewCreated")
    }

    override fun onResume() {
        super.onResume()
        Log.d("HOST", "DetailFragment onResume")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.d("HOST", "DetailFragment onHiddenChanged hidden=$hidden")
    }

    override fun onDestroyView() {
        Log.d("HOST", "DetailFragment onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("HOST", "DetailFragment onDestroy")
        super.onDestroy()
    }
}
