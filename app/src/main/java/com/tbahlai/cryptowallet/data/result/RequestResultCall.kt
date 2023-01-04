package com.tbahlai.cryptowallet.data.result

import com.tbahlai.cryptowallet.data.models.RequestResult
import com.tbahlai.cryptowallet.data.models.RequestResult.Companion.httpFailure
import com.tbahlai.cryptowallet.data.models.RequestResult.Companion.networkFailure
import com.tbahlai.cryptowallet.data.models.RequestResult.Companion.unknownFailure
import com.tbahlai.cryptowallet.data.utils.StatusCode
import kotlinx.coroutines.CancellationException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Response.success
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.Type

internal class RequestResultCall<T>(
    private val retrofit: Retrofit,
    private val annotations: Array<Annotation>,
    proxy: Call<T>,
    private val errorType: Type,
) : CallDelegate<T, Any>(proxy) {

    override fun enqueue(callback: Callback<Any>) {
        proxy.enqueue(ResultCallback(retrofit, annotations, this, callback, errorType))
    }

    override fun clone(): Call<Any> {
        return RequestResultCall(retrofit, annotations, proxy.clone(), errorType)
    }

    private class ResultCallback<T>(
        private val retrofit: Retrofit,
        private val annotations: Array<Annotation>,
        private val proxy: RequestResultCall<T>,
        private val callback: Callback<in Any>,
        private val errorType: Type,
    ) : Callback<T> {

        @Suppress("UNCHECKED_CAST", "NestedBlockDepth")
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                callback.onResponse(proxy, success(RequestResult.success(response.body() as T)))
                return
            }
            val errorResponseBody = response.errorBody()
            val hasErrorCode = response.code() in StatusCode.HTTP_CLIENT_FAILURE_RANGE
            val hasErrorBody = errorResponseBody != null && errorResponseBody.contentLength() != 0L
            val errorBody = if (hasErrorCode || hasErrorBody) {
                if (errorType == Unit::class.java || errorType == Any::class.java) {
                    @Suppress("RedundantUnitExpression")
                    Unit
                } else {
                    try {
                        retrofit
                            .responseBodyConverter<Any>(errorType, annotations)
                            .convert(errorResponseBody)
                    } catch (e: Throwable) {
                        if (e is CancellationException) {
                            callback.onFailure(proxy, e)
                            return
                        }
                        callback.onResponse(call, success(unknownFailure(e)))
                        return
                    }
                }
            } else null

            callback.onResponse(call, success(httpFailure(response.code(), errorBody)))
        }

        override fun onFailure(call: Call<T>, error: Throwable) {
            if (error is CancellationException) {
                callback.onFailure(proxy, error)
            } else {
                when (error) {
                    is IOException -> callback.onResponse(call, success(networkFailure(error)))
                    else -> callback.onResponse(call, success(unknownFailure(error)))
                }
            }
        }
    }
}
