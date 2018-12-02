package com.example.younghyupyun.radiobuttonswiching.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.younghyupyun.radiobuttonswiching.databases.PlantsRepository


class MainViewModelProviderFactory(private val repository: PlantsRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MainViewModel(repository) as T
}