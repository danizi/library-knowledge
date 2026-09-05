package com.example.emptyactivity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment

class ChildAFragment : Fragment(R.layout.fragment_child_a) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("HOST", "ChildA onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("HOST", "ChildA onViewCreated parentFM=${parentFragmentManager.javaClass.simpleName} childFM=${childFragmentManager.javaClass.simpleName}")
    }

    override fun onDestroyView() {
        Log.d("HOST", "ChildA onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("HOST", "ChildA onDestroy")
        super.onDestroy()
    }
}
