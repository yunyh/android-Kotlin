package com.example.younghyupyun.radiobuttonswiching.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.younghyupyun.radiobuttonswiching.constract.FILE_NAME
import com.example.younghyupyun.radiobuttonswiching.databases.AppDatabase
import com.example.younghyupyun.radiobuttonswiching.models.PlantsModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader


typealias PlantsTypeToken = TypeToken<List<PlantsModel>>

class PlantsBasedataWorker(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {

    private val typeToken = object : PlantsTypeToken() {}.type

    override fun doWork(): Result {
        applicationContext.assets.open(FILE_NAME).apply {
            return JsonReader(reader()).let {
                Gson().fromJson<List<PlantsModel>>(it, typeToken).also { list ->
                    AppDatabase.getInstance(applicationContext).plantsDAO().insertALL(list)
                }
                Result.SUCCESS
            }
        }
    }
}