package com.example.emptyactivity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.emptyactivity.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var pendingCity: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("HOST", "HomeFragment onCreate")
        // 在 onCreate 注册：即使暂时不在前台，Detail 回传时也能收到
        parentFragmentManager.setFragmentResultListener(REQUEST_KEY, this) { key, bundle ->
            val city = bundle.getString(KEY_CITY).orEmpty()
            Log.d("HOST", "Home 收到 Result key=$key city=$city")
            pendingCity = city
            view?.let { applyResult(it, city) }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("HOST", "HomeFragment onViewCreated")
        pendingCity?.let { applyResult(view, it) }
    }

    override fun onDestroyView() {
        Log.d("HOST", "HomeFragment onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("HOST", "HomeFragment onDestroy")
        super.onDestroy()
    }

    private fun applyResult(view: View, city: String) {
        val binding = FragmentHomeBinding.bind(view)
        binding.tvResult.text = "签收单：选了「$city」"
    }

    companion object {
        const val REQUEST_KEY = "city_pick"
        const val KEY_CITY = "city"
    }
}
