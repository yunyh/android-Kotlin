package com.base.yun.mytestapp.lifecycle

import android.annotation.SuppressLint
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import android.util.Log

/**
 * Created by YounghyubYun on 2017. 10. 4..
 */

const val TAG: String = "ActivityLifecycleObserver"

@SuppressLint("LongLogTag")
class ActivityLifecycleObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.d(TAG, "onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.d(TAG, "onPause")
    }
}