package com.example.emptyactivity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val vm: HostViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("HOST", "DetailFragment onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tv = view.findViewById<TextView>(R.id.tv_note)
        vm.note.observe(viewLifecycleOwner) { tv.text = it }
        view.findViewById<Button>(R.id.btn_write).setOnClickListener {
            vm.writeFromDetail()
        }
    }

    override fun onDestroy() {
        Log.d("HOST", "DetailFragment onDestroy")
        super.onDestroy()
    }
}
