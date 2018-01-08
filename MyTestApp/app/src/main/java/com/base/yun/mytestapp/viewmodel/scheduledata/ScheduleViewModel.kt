package com.base.yun.mytestapp.viewmodel.scheduledata

import android.app.Application
import com.base.yun.mytestapp.provider.ScheduleEntity
import com.base.yun.mytestapp.viewmodel.mydata.MyViewModel
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

    fun createMySchedule(title: String, desc: String, date: Long = System.currentTimeMillis()) {
        with(scheduleDao) {
            createSchedule(ScheduleEntity(0, title, desc, date))
        }
    }
}