package com.example.emptyactivity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.emptyactivity.databinding.FragmentParentBinding

class ParentFragment : Fragment(R.layout.fragment_parent) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("HOST", "ParentFragment onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("HOST", "ParentFragment onViewCreated")
        val binding = FragmentParentBinding.bind(view)
        binding.tvParent.text =
            "父隔断 · ParentFragment #${System.identityHashCode(this)}"

        binding.btnPushChildB.setOnClickListener {
            pushChildB()
        }

        if (savedInstanceState == null && childFragmentManager.findFragmentById(R.id.child_container) == null) {
            Log.d("HOST", "Parent 用 childFragmentManager 装 ChildA（根子页，不入子栈）")
            childFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.child_container, ChildAFragment(), "ChildA")
            }
        }
    }

    private fun pushChildB() {
        if (childFragmentManager.backStackEntryCount > 0) {
            Log.d("HOST", "子栈已有记录，先系统返回再推进")
            return
        }
        val current = childFragmentManager.findFragmentById(R.id.child_container)
        if (current is ChildBFragment) return
        Log.d("HOST", "Parent childFragmentManager：replace ChildB + addToBackStack")
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.child_container, ChildBFragment(), "ChildB")
            addToBackStack("ChildB")
        }
    }

    override fun onDestroyView() {
        Log.d("HOST", "ParentFragment onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("HOST", "ParentFragment onDestroy")
        super.onDestroy()
    }
}
