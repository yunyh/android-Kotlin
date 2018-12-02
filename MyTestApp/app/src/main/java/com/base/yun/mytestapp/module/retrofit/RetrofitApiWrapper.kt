package com.base.yun.mytestapp.module.retrofit

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object RetrofitApiWrapper {
    private const val GITHUB_URL = "https://api.github.com"
    val retrofit: Retrofit by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        Retrofit.Builder().baseUrl(GITHUB_URL).apply {
            client(OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
            }.build())
            addConverterFactory(
                    JacksonConverterFactory.create(ObjectMapper().apply {
                        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    }))
        }.build()
    }
}