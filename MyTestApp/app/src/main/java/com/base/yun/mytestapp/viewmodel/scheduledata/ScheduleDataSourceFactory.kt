package com.base.yun.mytestapp.viewmodel.scheduledata

import android.arch.paging.DataSource
import com.base.yun.mytestapp.provider.ScheduleDao
import com.base.yun.mytestapp.provider.ScheduleEntity


/**
 * Created by YoungHyup on 2018-01-08.
 */
class ScheduleDataSourceFactory(private val dao: ScheduleDao) : DataSource.Factory<Int, ScheduleEntity>() {
    override fun create(): DataSource<Int, ScheduleEntity> {
        TODO()
    }
}