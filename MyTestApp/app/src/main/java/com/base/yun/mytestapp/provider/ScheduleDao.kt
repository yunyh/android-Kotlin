package com.base.yun.mytestapp.provider

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.base.yun.mytestapp.model.ScheduleItemModel

/**
 * Created by YoungHyup on 2018-01-08.
 */

@Dao
interface ScheduleDao {

    @Insert
    fun createSchedule(schedule: ScheduleEntity)

    @Query("SELECT * FROM ${Contracts.Schedule.TABLE_NAME}")
    fun selectAll(): List<ScheduleEntity>

    @Update
    fun updateSchedule(schedule: ScheduleEntity)

    @Delete
    fun deleteSchedule(schedule: ScheduleEntity)

}