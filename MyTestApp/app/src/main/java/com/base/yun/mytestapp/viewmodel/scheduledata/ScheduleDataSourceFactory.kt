package com.base.yun.mytestapp.viewmodel.scheduledata

import android.arch.paging.DataSource
import android.support.annotation.BinderThread
import android.support.annotation.MainThread
import com.base.yun.mytestapp.provider.ScheduleEntity


/**
 * Created by YoungHyup on 2018-01-08.
 */
class ScheduleDataSourceFactory(list: List<ScheduleEntity>) : DataSource.Factory<Int, ScheduleEntity> {

    private val myList = list

    override fun create(): DataSource<Int, ScheduleEntity> {
        return ScheduleDataSource(myList)
    }

}