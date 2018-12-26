package com.example.younghyupyun.myapplication.base.viewmodel

import android.content.Intent
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.younghyupyun.myapplication.base.events.EventObserver
import com.example.younghyupyun.myapplication.base.events.SingleEvent

abstract class BaseViewModel protected constructor(protected val ui: IBaseActivityUi) : ViewModel() {

    protected val singleEvent = SingleEvent()

    fun observeSingleEvent(lifecycleOwner: LifecycleOwner, observer: EventObserver) {
        singleEvent.observe(lifecycleOwner, observer)
    }

    override fun onCleared() {
        super.onCleared()
        singleEvent.clear()
    }

    fun setIntent(intent: Intent?) {

    }
}