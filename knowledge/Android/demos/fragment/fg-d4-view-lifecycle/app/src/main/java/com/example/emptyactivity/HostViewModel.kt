package com.example.emptyactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/** Activity 作用域：切页 / 拆 view 后信箱还在。 */
class HostViewModel : ViewModel() {
    private val _note = MutableLiveData("信箱空 · 点「宿主发信箱」")
    val note: LiveData<String> = _note
    private var n = 0

    fun post() {
        n++
        _note.value = "信箱第 $n 封 · ${System.currentTimeMillis() % 100000}"
    }
}
