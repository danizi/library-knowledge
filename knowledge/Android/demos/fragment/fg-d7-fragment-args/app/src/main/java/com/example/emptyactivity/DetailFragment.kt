package com.example.emptyactivity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.emptyactivity.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = arguments?.getString(ARG_ID)
        Log.d(
            "HOST",
            "DetailFragment onCreate hash=${System.identityHashCode(this)} " +
                "id=$id restored=${savedInstanceState != null}"
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)
        val id = requireArguments().getString(ARG_ID).orEmpty()
        val hash = System.identityHashCode(this)
        binding.tvId.text = "arguments id = $id"
        binding.tvHash.text = "本实例 hash = #$hash"
        binding.tvHint.text =
            if (savedInstanceState != null) {
                "刚旋转/恢复：实例可能是新的，但 id 从 arguments 读回"
            } else {
                "空构造 + setArguments；旋转后对照 hash 与 id"
            }
        Log.d("HOST", "DetailFragment onViewCreated id=$id hash=$hash")
    }

    override fun onDestroyView() {
        Log.d("HOST", "DetailFragment onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("HOST", "DetailFragment onDestroy hash=${System.identityHashCode(this)}")
        super.onDestroy()
    }

    companion object {
        const val ARG_ID = "item_id"

        fun newInstance(id: String): DetailFragment =
            DetailFragment().apply {
                arguments = bundleOf(ARG_ID to id)
            }
    }
}
