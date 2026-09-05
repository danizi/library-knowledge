package com.example.emptyactivity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.emptyactivity.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val vm: HostViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("HOST", "DetailFragment onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)
        vm.note.observe(viewLifecycleOwner) { binding.tvNote.text = it }
        binding.btnWrite.setOnClickListener {
            vm.writeFromDetail()
        }
    }

    override fun onDestroy() {
        Log.d("HOST", "DetailFragment onDestroy")
        super.onDestroy()
    }
}
