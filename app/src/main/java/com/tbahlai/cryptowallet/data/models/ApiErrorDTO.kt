package com.tbahlai.cryptowallet.data.models

import com.google.gson.annotations.SerializedName
import com.tbahlai.cryptowallet.domain.models.ApiError

class ApiErrorDTO(
    @SerializedName("message")
    val message: String? = null
) {
    fun toApiError(): ApiError {
        return ApiError(message)
    }
}