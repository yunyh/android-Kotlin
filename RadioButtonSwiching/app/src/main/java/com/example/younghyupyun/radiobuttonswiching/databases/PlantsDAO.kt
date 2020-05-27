package com.example.younghyupyun.radiobuttonswiching.databases

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.younghyupyun.radiobuttonswiching.models.PlantsModel

@Dao
interface PlantsDAO {
    @Query("SELECT * FROM plants")
    fun getAll(): List<PlantsModel>

    @Insert
    fun insertALL(vararg plantsModel: PlantsModel)

    @Insert
    fun insertALL(plantsModel: List<PlantsModel>)

    @Query("SELECT * FROM plants")
    fun getAllForLiveData(): LiveData<List<PlantsModel>>
}