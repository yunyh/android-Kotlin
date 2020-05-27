package com.base.yun.mytestapp.module.retrofit.factory

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class CoroutineCallAdapterFactory : CallAdapter.Factory() {

    companion object {
        @JvmStatic
        @JvmName("create")
        operator fun invoke() = CoroutineCallAdapterFactory()
    }

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        return when {
            Deferred::class.java != getRawType(returnType) -> null
            returnType !is ParameterizedType -> throw IllegalStateException("Deferred is parameterizedType")
            else -> {
                val responseType = getParameterUpperBound(0, returnType)
                when (val rawDeferredType = getRawType(responseType)) {
                    Response::class.java -> {
                        if (rawDeferredType !is ParameterizedType) {
                            throw IllegalStateException("Deferred is parameterizedType")
                        } else {
                            ResponseCallAdapter<Any>(responseType)
                        }
                    }
                    else -> BodyCallAdapter<Any>(responseType)
                }
            }
        }
    }

    private class BodyCallAdapter<T>(private val responseType: Type) : CallAdapter<T, Deferred<T>> {
        override fun responseType(): Type = responseType

        override fun adapt(call: Call<T>): Deferred<T> = CompletableDeferred<T>().also { deferred ->
            deferred.invokeOnCompletion {
                if (deferred.isCancelled) {
                    call.cancel()
                }
            }

            call.enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    deferred.completeExceptionally(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (response.isSuccessful) {
                        deferred.complete(response.body() ?: return)
                    } else {
                        deferred.completeExceptionally(HttpException(response))
                    }
                }
            })
        }
    }

    private class ResponseCallAdapter<T>(private val responseType: Type) : CallAdapter<T, Deferred<Response<T>>> {

        override fun responseType(): Type = responseType

        override fun adapt(call: Call<T>): Deferred<Response<T>> = CompletableDeferred<Response<T>>().also { deferred ->
            deferred.invokeOnCompletion {
                if (deferred.isCancelled) {
                    call.cancel()
                }
            }

            call.enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    deferred.completeExceptionally(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    deferred.complete(response)
                }
            })
        }
    }
}