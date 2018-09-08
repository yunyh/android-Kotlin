package com.base.yun.mytestapp.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.util.Log
import com.base.yun.mytestapp.model.GitHubEventsModel
import com.base.yun.mytestapp.model.RepositoryModel
import com.base.yun.mytestapp.moduel.retrofit.services.BaseServices
import com.base.yun.mytestapp.moduel.retrofit.services.GithubUsersServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivityViewModel"

class MainActivityViewModel : ViewModel() {

    val id = ObservableField<String>()

    val eventsLiveData = MutableLiveData<List<GitHubEventsModel>>()

    fun getService() {
        BaseServices.instance.getRepos("yunyh", "android-ui").enqueue(object : Callback<RepositoryModel> {
            override fun onFailure(call: Call<RepositoryModel>, t: Throwable) {
            }

            override fun onResponse(call: Call<RepositoryModel>, response: Response<RepositoryModel>) {
                response.run {
                    if (isSuccessful) {
                        id.set(body()?.id.toString())
                    }
                }
            }
        })

    }

    fun getReceivedEvents(username: String) {
        GithubUsersServices.instance.getUsersEvents("yunyh").enqueue(object : Callback<List<GitHubEventsModel>> {
            override fun onFailure(call: Call<List<GitHubEventsModel>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<List<GitHubEventsModel>>, response: Response<List<GitHubEventsModel>>) {
                response.run {
                    if (isSuccessful) {
                        eventsLiveData.postValue(body())
                    }
                }
            }

        })
    }

}