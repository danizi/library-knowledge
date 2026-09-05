package com.example.emptyactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/** Activity 作用域：两个 Fragment 共用，切页不丢。 */
class HostViewModel : ViewModel() {
    private val _note = MutableLiveData("公共信箱还是空的")
    val note: LiveData<String> = _note

    private var switches = 0

    fun markSwitch(page: String) {
        switches++
        _note.value = "已切 $switches 次 · 当前 $page"
    }

    fun writeFromDetail() {
        _note.value = "Detail 写过：宿主还在，信箱还在"
    }
}
