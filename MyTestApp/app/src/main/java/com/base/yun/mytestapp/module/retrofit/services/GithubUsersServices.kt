package com.base.yun.mytestapp.module.retrofit.services

import com.base.yun.mytestapp.model.GitHubEventsModel
import com.base.yun.mytestapp.module.retrofit.RetrofitApiWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubUsersServices {

    companion object {
        val instance: GithubUsersServices by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitApiWrapper.retrofit.create(GithubUsersServices::class.java)
        }
    }

    @GET("/users/{user}")
    fun getUsers(@Path("user") user: String)

    @GET("/users/{user}/received_events")
    fun getUsersReceivedEvents(@Path("user") user: String)

    @GET("/users/{user}/events")
    fun getUsersEvents(@Path("user") user: String): Call<List<GitHubEventsModel>>
}