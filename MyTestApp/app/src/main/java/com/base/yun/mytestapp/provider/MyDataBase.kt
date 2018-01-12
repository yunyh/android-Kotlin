package com.base.yun.mytestapp.provider

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by YoungHyup on 2018-01-08.
 */

@Database(entities = [ScheduleEntity::class], version = 1)
abstract class MyDataBase : RoomDatabase() {
    abstract fun myScheduleDao(): ScheduleDao

    companion object {
        private var database: MyDataBase? = null

        fun provider(context: Context): MyDataBase {
            synchronized(this) {
                if (database == null) {
                    database = Room.databaseBuilder(context, MyDataBase::class.java, "my_database.db").allowMainThreadQueries().build()
                }
                return database!!
            }
        }
    }
}