package com.example.emptyactivity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.emptyactivity.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("HOST", "HomeFragment onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("HOST", "HomeFragment onViewCreated")
        val binding = FragmentHomeBinding.bind(view)
        binding.btnToDetail.setOnClickListener {
            Log.d("HOST", "Home navigate → Detail（NavController，不是手写 replace）")
            findNavController().navigate(R.id.action_home_to_detail)
        }
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
