package com.base.yun.mytestapp.viewmodel.mydata

import android.app.Application
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by YounghyubYun on 2018. 1. 8..
 */

class ScheduleViewModel(app: Application) : MyViewModel(app) {

    private companion object {
        val format: SimpleDateFormat = SimpleDateFormat("yyyy MM dd", Locale.KOREA)
    }

    fun setCurrentTimeMileSecond(): String {
        return format.format(System.currentTimeMillis())
    }
}