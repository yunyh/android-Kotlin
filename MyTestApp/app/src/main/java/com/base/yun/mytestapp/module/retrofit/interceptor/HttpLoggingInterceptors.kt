package com.base.yun.mytestapp.module.retrofit.interceptor

import okhttp3.logging.HttpLoggingInterceptor

val httpLoggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
