package com.example.emptyactivity

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ConfirmDialogFragment : DialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("HOST", "ConfirmDialogFragment onCreate restored=${savedInstanceState != null}")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d("HOST", "ConfirmDialogFragment onCreateDialog")
        return AlertDialog.Builder(requireContext())
            .setTitle("DialogFragment")
            .setMessage("旋转屏幕，对话框应还在（不是裸 Dialog）")
            .setPositiveButton("知道了", null)
            .create()
    }

    override fun onDestroy() {
        Log.d("HOST", "ConfirmDialogFragment onDestroy")
        super.onDestroy()
    }
}
