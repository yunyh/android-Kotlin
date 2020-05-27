package com.base.yun.mytestapp.provider

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by YoungHyup on 2018-01-08.
 */


@Entity(tableName = Contracts.Schedule.TABLE_NAME)
data class ScheduleEntity constructor(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = Contracts.Schedule.COLUMN_ID)
        val id: Long = 0L,
        @ColumnInfo(name = Contracts.Schedule.COLUMN_TITLE)
        val title: String,
        @ColumnInfo(name = Contracts.Schedule.COLUMN_DESC)
        val desc: String,
        @ColumnInfo(name = Contracts.Schedule.COLUMN_DATE)
        val date: Long)
