package com.example.younghyupyun.radiobuttonswiching.databases

class PlantsRepository  constructor(private val plantsDAO: PlantsDAO) {

    fun getPlants() = plantsDAO.getAll()

    fun getPlantsLiveData() = plantsDAO.getAllForLiveData()
}