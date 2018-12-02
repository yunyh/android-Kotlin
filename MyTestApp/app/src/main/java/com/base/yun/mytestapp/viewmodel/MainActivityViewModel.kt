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
import kotlinx.coroutines.*
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

    fun getService() = launch(coroutineContext) {
        /*BaseServices.INSTANCE.getRepos("yunyh", "android-ui").enqueue(object : Callback<RepositoryModel> {
            override fun onFailure(call: Call<RepositoryModel>, t: Throwable) {
                if (BuildConfig.DEBUG) {
                    t.printStackTrace()
                }
            }

            override fun onResponse(call: Call<RepositoryModel>, response: Response<RepositoryModel>) {
                response.run {
                    if (isSuccessful) {
                        id.set(body()?.id.toString())
                    }
                }
            }
        })*/
        val result = gitRepo.getService()
        result.onSuccess {
            id.set(it.id.toString())
            Log.d(TAG, "${it.id}")
        }
                .onFailure {

                }
                .onError {
                    if (BuildConfig.DEBUG) {
                        it.printStackTrace()
                    }
                }
    }

    fun getReceivedEvents(username: String) = launch(context = coroutineContext) {
        /*   GithubUsersServices.instance.getUsersEvents("yunyh").enqueue(object : Callback<List<GitHubEventsModel>> {
               override fun onFailure(call: Call<List<GitHubEventsModel>>, t: Throwable) {
                   if (BuildConfig.DEBUG) {
                       t.printStackTrace()
                   }
               }

               override fun onResponse(call: Call<List<GitHubEventsModel>>, response: Response<List<GitHubEventsModel>>) {
                   response.run {
                       if (isSuccessful) {
                           eventsLiveData.postValue(body())
                       }
                   }
               }
           })*/
        loadingProgressBar.set(true)
        gitRepo.getReceivedEvents(username).onSuccess { eventsLiveData.postValue(it) }.onError { it.printStackTrace() }
        loadingProgressBar.set(false)
    }
}