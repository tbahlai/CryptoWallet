package com.tbahlai.cryptowallet.data.interceptors

import com.tbahlai.cryptowallet.data.utils.StatusCode.BAD_REQUEST
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject

class ErrorsHandlerInterceptor @Inject constructor(
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        var newResponse = response

        if (response.code >= BAD_REQUEST) {
            val reasonMessage = response.body?.string() ?: "Unexpected error occurred"
            newResponse = response.newBuilder()
                .message(reasonMessage)
                .body(reasonMessage.toResponseBody(response.body!!.contentType()))
                .build()
        }

        return newResponse
    }
}