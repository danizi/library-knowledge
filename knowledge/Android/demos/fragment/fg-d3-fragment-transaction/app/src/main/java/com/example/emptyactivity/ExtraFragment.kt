package com.example.emptyactivity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment

class ExtraFragment : Fragment(R.layout.fragment_extra) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("HOST", "ExtraFragment onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("HOST", "ExtraFragment onViewCreated")
    }

    override fun onDestroyView() {
        Log.d("HOST", "ExtraFragment onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("HOST", "ExtraFragment onDestroy")
        super.onDestroy()
    }
}
