package com.base.yun.mytestapp

import android.app.Application
import android.support.multidex.MultiDex

/**
 * Created by YoungHyup on 2018-01-08.
 */

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
    }
}