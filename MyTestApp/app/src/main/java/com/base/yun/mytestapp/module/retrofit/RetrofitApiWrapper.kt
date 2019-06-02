package com.base.yun.mytestapp.module.retrofit

import com.base.yun.mytestapp.module.retrofit.interceptor.httpLoggingInterceptor
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object RetrofitApiWrapper {
    private const val GITHUB_URL = "https://api.github.com"
    val retrofit: Retrofit by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        Retrofit.Builder().baseUrl(GITHUB_URL).apply {
            client(OkHttpClient.Builder().apply {
                addInterceptor(httpLoggingInterceptor)
            }.build())
            addConverterFactory(
                    JacksonConverterFactory.create(ObjectMapper().apply {
                        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    }))
        }.build()
    }
}