package com.base.yun.mytestapp.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
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


class MainActivityViewModel(
        private val gitRepo: GithubRepository = GithubRepository()) : ViewModel(), CoroutineScope {

    companion object {
        const val TAG = "MainActivityViewModel"
    }

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    val id = ObservableField<String>()
    private val _eventsLiveData = MutableLiveData<List<GitHubEventsModel>>()
    val eventsLiveData: LiveData<List<GitHubEventsModel>>
        get() = _eventsLiveData

    val loadingProgressBar = ObservableBoolean(false)

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
        gitRepo.getReceivedEvents(username)
                .onSuccess(_eventsLiveData::postValue)
                .onFailure { Log.d(TAG, "onFailure") }
                .onError { it.printStackTrace() }
        Log.d(TAG, "loadingProgressBar")
        loadingProgressBar.set(false)
    }
}