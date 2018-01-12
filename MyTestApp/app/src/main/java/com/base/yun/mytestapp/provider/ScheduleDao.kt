package com.base.yun.mytestapp.provider

import android.arch.persistence.room.*
import android.support.annotation.AnyThread
import com.base.yun.mytestapp.viewmodel.scheduledata.ScheduleDataSourceFactory

/**
 * Created by YoungHyup on 2018-01-08.
 */

@Dao
interface ScheduleDao {

    @Insert
    fun createSchedule(schedule: ScheduleEntity)

    @Query("SELECT * FROM ${Contracts.Schedule.TABLE_NAME}")
    @AnyThread
    fun selectAll(): List<ScheduleEntity>

    @Update
    fun updateSchedule(schedule: ScheduleEntity)

    @Delete
    fun deleteSchedule(schedule: ScheduleEntity)

    @Query("SELECT * FROM ${Contracts.Schedule.TABLE_NAME}")
    @AnyThread
    fun getAll(): ScheduleDataSourceFactory

}