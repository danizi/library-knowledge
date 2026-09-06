package com.example.emptyactivity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.emptyactivity.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("HOST", "DetailFragment onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("HOST", "DetailFragment onViewCreated")
        val binding = FragmentDetailBinding.bind(view)
        binding.tvDetail.text =
            "从 graph action 进来 · 系统返回应 pop 回 Home\n本实例 #${System.identityHashCode(this)}"
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
