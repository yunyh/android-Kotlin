package com.base.yun.mytestapp.moduel.retrofit.services

import com.base.yun.mytestapp.model.RepositoryModel
import com.base.yun.mytestapp.moduel.retrofit.RetrofitApiWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BaseServices {

    companion object {
        val instance: BaseServices by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitApiWrapper.retrofit.create(BaseServices::class.java)
        }
    }

    @GET("/repos/{owner}/{repos}")
    fun getRepos(@Path("owner") owner: String, @Path("repos") repos: String): Call<RepositoryModel>
}