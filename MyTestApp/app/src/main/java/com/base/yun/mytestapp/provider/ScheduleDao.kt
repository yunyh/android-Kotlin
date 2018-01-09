package com.base.yun.mytestapp.provider

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.*
import android.support.annotation.WorkerThread
import com.base.yun.mytestapp.model.ScheduleItemModel
import com.base.yun.mytestapp.viewmodel.scheduledata.ScheduleDataSourceFactory

/**
 * Created by YoungHyup on 2018-01-08.
 */

@Dao
interface ScheduleDao {

    @Insert
    @WorkerThread
    fun createSchedule(schedule: ScheduleEntity)

    @Query("SELECT * FROM ${Contracts.Schedule.TABLE_NAME}")
    @WorkerThread
    fun selectAll(): List<ScheduleEntity>

    @Update
    fun updateSchedule(schedule: ScheduleEntity)

    @Delete
    fun deleteSchedule(schedule: ScheduleEntity)

    @Query("SELECT * FROM ${Contracts.Schedule.TABLE_NAME}")
    @WorkerThread
    fun getAll(): DataSource.Factory<Int, ScheduleEntity>

}