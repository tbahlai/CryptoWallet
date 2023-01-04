package com.tbahlai.cryptowallet.data.adapters

import com.tbahlai.cryptowallet.data.models.RequestResult
import com.tbahlai.cryptowallet.data.result.RequestResultCall
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class RequestResultAdapterFactory : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java || returnType !is ParameterizedType) return null

        val callInnerType = getParameterUpperBound(0, returnType)
        if (getRawType(callInnerType) != RequestResult::class.java) return null

        if (callInnerType !is ParameterizedType) {
            return RequestResultCallAdapter<Any?>(retrofit, annotations, Nothing::class.java, Nothing::class.java)
        }

        var successInnerType = getParameterUpperBound(0, callInnerType)
        if (successInnerType == Any::class.java) {
            successInnerType = Unit::class.java
        }
        val errorInnerType = getParameterUpperBound(1, callInnerType)

        return RequestResultCallAdapter<Any?>(retrofit, annotations, successInnerType, errorInnerType)
    }

    private class RequestResultCallAdapter<R>(
        private val retrofit: Retrofit,
        private val annotations: Array<Annotation>,
        private val successType: Type,
        private val errorType: Type,
    ) : CallAdapter<R, Any> {

        override fun responseType(): Type = successType

        override fun adapt(call: Call<R>): Any = RequestResultCall(retrofit, annotations, call, errorType)
    }
}
