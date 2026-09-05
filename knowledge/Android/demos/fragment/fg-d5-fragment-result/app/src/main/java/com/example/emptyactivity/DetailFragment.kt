package com.example.emptyactivity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
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
        binding.btnSendResult.setOnClickListener {
            val city = binding.etCity.text?.toString()?.trim().orEmpty().ifEmpty { "未填写" }
            Log.d("HOST", "Detail setFragmentResult city=$city")
            setFragmentResult(
                HomeFragment.REQUEST_KEY,
                bundleOf(HomeFragment.KEY_CITY to city)
            )
            parentFragmentManager.popBackStack()
        }
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
