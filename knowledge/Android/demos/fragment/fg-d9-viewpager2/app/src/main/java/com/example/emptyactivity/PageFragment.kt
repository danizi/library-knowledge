package com.example.emptyactivity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.emptyactivity.databinding.FragmentPageBinding

class PageFragment : Fragment(R.layout.fragment_page) {
    private val pageIndex: Int
        get() = requireArguments().getInt(ARG_INDEX)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(
            "HOST",
            "Page$pageIndex onCreate hash=${System.identityHashCode(this)} " +
                "restored=${savedInstanceState != null}"
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPageBinding.bind(view)
        val hash = System.identityHashCode(this)
        binding.tvTitle.text = "隔断 · Page$pageIndex"
        binding.tvHash.text = "本实例 hash = #$hash"
        binding.pageRoot.setBackgroundColor(
            when (pageIndex) {
                0 -> 0xFFE3F2FD.toInt()
                1 -> 0xFFFFF3E0.toInt()
                else -> 0xFFE8F5E9.toInt()
            }
        )
        Log.d("HOST", "Page$pageIndex onViewCreated hash=$hash")
    }

    override fun onResume() {
        super.onResume()
        Log.d("HOST", "Page$pageIndex onResume hash=${System.identityHashCode(this)}")
    }

    override fun onPause() {
        Log.d("HOST", "Page$pageIndex onPause hash=${System.identityHashCode(this)}")
        super.onPause()
    }

    override fun onDestroyView() {
        Log.d("HOST", "Page$pageIndex onDestroyView hash=${System.identityHashCode(this)}")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("HOST", "Page$pageIndex onDestroy hash=${System.identityHashCode(this)}")
        super.onDestroy()
    }

    companion object {
        private const val ARG_INDEX = "page_index"

        fun newInstance(index: Int): PageFragment =
            PageFragment().apply {
                arguments = bundleOf(ARG_INDEX to index)
            }
    }
}
