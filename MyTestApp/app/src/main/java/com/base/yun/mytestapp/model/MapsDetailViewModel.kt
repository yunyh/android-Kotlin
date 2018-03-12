package com.base.yun.mytestapp.model

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.base.yun.mytestapp.viewmodel.base.MyViewModel

/**
 * Created by mark.yun on 2018. 3. 12..
 */
class MapsDetailViewModel(app: Application) : MyViewModel(app) {
    private val data = MutableLiveData<String>()

    fun getData(): MutableLiveData<String> {
        return data
    }
}