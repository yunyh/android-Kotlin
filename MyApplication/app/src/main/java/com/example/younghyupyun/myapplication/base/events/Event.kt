package com.example.younghyupyun.myapplication.base.events

import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

sealed class Event {
    data class ToastEvent(val message: String, val duration: Int = Toast.LENGTH_LONG) : Event()

    data class SnackbarEvent(val message: String, val duration: Int = Snackbar.LENGTH_LONG) : Event()

    data class AlertDialogEvent(
        val title: String?,
        val message: String,
        val positiveButtonText: String = "OK",
        val negativeButtonText: String = "Cancel"
    ) : Event()
}

inline fun Event.showToast(toast: (Event.ToastEvent) -> Unit): Event {
    if (this is Event.ToastEvent) {
        toast(this)
    }
    return this
}