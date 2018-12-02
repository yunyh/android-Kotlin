package com.example.younghyupyun.radiobuttonswiching.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.younghyupyun.radiobuttonswiching.models.PlantsModel
import com.example.younghyupyun.radiobuttonswiching.workers.PlantsBasedataWorker

@Database(entities = [PlantsModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "database.db"
                ).addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        OneTimeWorkRequest.Builder(PlantsBasedataWorker::class.java).build().let {
                            WorkManager.getInstance().enqueue(it)
                        }
                    }
                }).build().also { INSTANCE = it }
            }
        }
    }

    abstract fun plantsDAO(): PlantsDAO
}