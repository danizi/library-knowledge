package com.example.emptyactivity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.emptyactivity.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val vm: HostViewModel by activityViewModels()
    private var dangerObserver: Observer<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("HOST", "HomeFragment onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("HOST", "HomeFragment onViewCreated")
        val binding = FragmentHomeBinding.bind(view)
        // 安全：跟 view 走，onDestroyView 后不再回调
        vm.note.observe(viewLifecycleOwner) { text ->
            Log.d("HOST", "Home SAFE(viewLifecycleOwner) 收到: $text")
            binding.tvNote.text = text
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("HOST", "HomeFragment onResume")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.d("HOST", "HomeFragment onHiddenChanged hidden=$hidden")
    }

    override fun onDestroyView() {
        Log.d("HOST", "HomeFragment onDestroyView（人可能还在栈里）")
        super.onDestroyView()
    }

    override fun onDestroy() {
        dangerObserver?.let {
            vm.note.removeObserver(it)
            dangerObserver = null
            Log.d("HOST", "危险 observeForever 已在 onDestroy 摘掉")
        }
        Log.d("HOST", "HomeFragment onDestroy")
        super.onDestroy()
    }

    /**
     * 反例：observeForever 不跟生命周期。
     * Fragment 入栈后 STOPPED、view 已拆，信箱一来仍回调；再 requireView() 就崩。
     * （用 this 观察时 STOPPED 不会回调，演示不出崩点，所以这里用 forever。）
     */
    fun registerDangerousObserver() {
        if (dangerObserver != null) {
            Log.d("HOST", "危险观察已注册过")
            return
        }
        val obs = Observer<String> { text ->
            Log.d("HOST", "Home DANGER(observeForever) 收到: $text（人还在，不管 STARTED 与否）")
            try {
                FragmentHomeBinding.bind(requireView()).tvNote.text = "危险写入 $text"
                Log.d("HOST", "意外：view 还在，没崩")
            } catch (e: IllegalStateException) {
                Log.e(
                    "HOST",
                    "崩点：view 已销毁，requireView() 抛 ${e.javaClass.simpleName} — 所以要用 viewLifecycleOwner"
                )
            }
        }
        dangerObserver = obs
        vm.note.observeForever(obs)
        Log.d("HOST", "已 observeForever；先 replace+入栈，再点「宿主发信箱」")
    }
}
