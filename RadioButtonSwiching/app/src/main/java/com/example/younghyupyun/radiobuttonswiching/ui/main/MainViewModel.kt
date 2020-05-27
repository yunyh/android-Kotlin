package com.example.younghyupyun.radiobuttonswiching.ui.main

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.younghyupyun.radiobuttonswiching.databases.PlantsRepository

class MainViewModel internal constructor(private val repository: PlantsRepository) : ViewModel() {
    val showText = ObservableField<String>()

    val plantsList = repository.getPlantsLiveData()
}