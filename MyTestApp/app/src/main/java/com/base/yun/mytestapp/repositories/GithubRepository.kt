package com.base.yun.mytestapp.repositories

import com.base.yun.mytestapp.BuildConfig
import com.base.yun.mytestapp.model.GitHubEventsModel
import com.base.yun.mytestapp.model.RepositoryModel
import com.base.yun.mytestapp.module.retrofit.services.BaseServices
import com.base.yun.mytestapp.module.retrofit.services.GithubUsersServices
import com.base.yun.mytestapp.module.retrofit.wrapper.Error
import com.base.yun.mytestapp.module.retrofit.wrapper.ResponseResult
import com.base.yun.mytestapp.module.retrofit.wrapper.Success
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GithubRepository : CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    suspend fun getService(): ResponseResult<RepositoryModel> = suspendCoroutine {
        BaseServices.INSTANCE.getRepos("yunyh", "android-ui").enqueue(object : Callback<RepositoryModel> {
            override fun onFailure(call: Call<RepositoryModel>, t: Throwable) {
                it.resume(Error(t))
            }

            override fun onResponse(call: Call<RepositoryModel>, response: Response<RepositoryModel>) {
                if (response.isSuccessful) {
                    response.body()?.let { body -> it.resume(Success(body)) }
                }
            }
        })
    }

    suspend fun getReceivedEvents(userName: String): ResponseResult<List<GitHubEventsModel>> = suspendCoroutine {
        GithubUsersServices.instance.getUsersEvents("yunyh").enqueue(object : Callback<List<GitHubEventsModel>> {
            override fun onFailure(call: Call<List<GitHubEventsModel>>, t: Throwable) {
                it.resume(Error(t))
            }

            override fun onResponse(call: Call<List<GitHubEventsModel>>, response: Response<List<GitHubEventsModel>>) {
                if (response.isSuccessful) {
                    response.body()?.let { body -> it.resume(Success(body)) }
                }
            }
        })
    }
}