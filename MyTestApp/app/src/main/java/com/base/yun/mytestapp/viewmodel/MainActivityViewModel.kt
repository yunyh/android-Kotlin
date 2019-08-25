package com.base.yun.mytestapp.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.base.yun.mytestapp.BuildConfig
import com.base.yun.mytestapp.model.GitHubEventsModel
import com.base.yun.mytestapp.module.retrofit.wrapper.onError
import com.base.yun.mytestapp.module.retrofit.wrapper.onFailure
import com.base.yun.mytestapp.module.retrofit.wrapper.onSuccess
import com.base.yun.mytestapp.repositories.GithubRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class MainActivityViewModel : ViewModel(), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    companion object {
        const val TAG = "MainActivityViewModel"
    }

    val id = ObservableField<String>()
    val eventsLiveData = MutableLiveData<List<GitHubEventsModel>>()

    val loadingProgressBar = ObservableBoolean(false)

    private val gitRepo = GithubRepository()

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }

    fun getService() = launch {
        val result = gitRepo.getService()
        result.onSuccess {
            id.set(it.id.toString())
            Log.d(TAG, "${it.id}")
        }.onFailure {
        }.onError {
            if (BuildConfig.DEBUG) {
                it.printStackTrace()
            }
        }
    }

    fun getReceivedEvents(username: String) = launch {
        loadingProgressBar.set(true)
        /* gitRepo.getReceivedEvents(username).run {
             eventsLiveData.postValue(this)
         }*/
        loadingProgressBar.set(false)
        gitRepo.getReceivedEvents(username).onSuccess {
            Log.d(TAG, "onSuccess")
            eventsLiveData.postValue(it)
        }.onFailure { Log.d(TAG, "onFailure") }.onError { it.printStackTrace() }
        Log.d(TAG, "loadingProgressBar")
        loadingProgressBar.set(false)
    }
}