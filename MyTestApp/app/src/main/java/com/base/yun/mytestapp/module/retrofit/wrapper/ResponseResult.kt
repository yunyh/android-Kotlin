package com.base.yun.mytestapp.module.retrofit.wrapper

import kotlinx.coroutines.coroutineScope
import retrofit2.Response

sealed class ResponseResult<out T : Any>

data class Success<out T : Any>(val data: T) : ResponseResult<T>()

data class Failure<out T : Any>(val data: T?) : ResponseResult<T>()

data class Error(val throwable: Throwable) : ResponseResult<Nothing>()

inline fun <T : Any> ResponseResult<T>.onSuccess(action: (T) -> Unit): ResponseResult<T> {
    if (this is Success) {
        action(data)
    }
    return this
}

inline fun <T : Any> ResponseResult<T>.onFailure(action: (T?) -> Unit): ResponseResult<T> {
    if (this is Failure) {
        action(data)
    }
    return this
}

inline fun <T : Any> ResponseResult<T>.onError(action: (Throwable) -> Unit) {
    if (this is Error) {
        action(throwable)
    }
}

suspend fun <T : Any> Response<T>.mapResultResult(): ResponseResult<T> = coroutineScope {
    if (isSuccessful) {
        val body = body()
        if (body == null) {
            Failure(null)
        } else {
            Success(body)
        }
    } else {
        Failure(null)
    }
}
