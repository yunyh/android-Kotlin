package com.example.younghyupyun.myapplication.base.events

import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.snackbar.Snackbar

class SingleEvent {

    private var eventObserver: EventObserver? = null
    private var lifecycleOwner: LifecycleOwner? = null

    fun observe(lifecycleOwner: LifecycleOwner, eventObserver: EventObserver) {
        this.eventObserver = eventObserver
        this.lifecycleOwner = lifecycleOwner
    }

    fun clear() {
        lifecycleOwner = null
        eventObserver = null
    }

    fun showToast(message: String, duration: Int = Toast.LENGTH_LONG) =
        notifyInternal(Event.ToastEvent(message, duration))

    fun showSnackbar(message: String, duration: Int = Snackbar.LENGTH_LONG) =
        notifyInternal(Event.SnackbarEvent(message, duration))

    fun showAlertDialog(title: String, message: String, positiveButtonText: String, negativeButtonText: String) =
        notifyInternal(Event.AlertDialogEvent(title, message, positiveButtonText, negativeButtonText))

    private fun notifyInternal(event: Event) {
        lifecycleOwner?.apply {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
                eventObserver?.onAction(event)
            }
        }
    }
}