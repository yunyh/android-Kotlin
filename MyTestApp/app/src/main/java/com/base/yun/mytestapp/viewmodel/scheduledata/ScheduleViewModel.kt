package com.base.yun.mytestapp.viewmodel.scheduledata

import android.app.Application
import android.util.Log
import com.base.yun.mytestapp.provider.ScheduleEntity
import com.base.yun.mytestapp.viewmodel.base.MyViewModel
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors

/**
 * Created by YounghyubYun on 2018. 1. 8..
 */

private const val TAG: String = "ScheduleViewModel"

class ScheduleViewModel(app: Application) : MyViewModel(app) {

    private companion object {
        val format: SimpleDateFormat = SimpleDateFormat("yyyy MM dd", Locale.KOREA)
    }

    fun setCurrentTimeMileSecond(): String {
        return format.format(System.currentTimeMillis())
    }

    fun setChangedTime(year: Int, month: Int, dayOfMonth: Int): String {
        val cal = Calendar.getInstance()
        cal.set(year, month, dayOfMonth)
        Log.d(TAG, cal.time.toString())
        return format.format(cal.time)
    }

    fun createMySchedule(title: String, desc: String, date: Long = format.calendar.time.time) {
        val writeService = Executors.newSingleThreadExecutor()
        writeService.execute({
            with(scheduleDao) {
                createSchedule(ScheduleEntity(0, title, desc, date))
            }
        })

        writeService.shutdown()
    }
}