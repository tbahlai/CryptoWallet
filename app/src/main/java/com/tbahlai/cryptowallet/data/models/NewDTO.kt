package com.tbahlai.cryptowallet.data.models

import com.google.gson.annotations.SerializedName
import com.tbahlai.cryptowallet.domain.models.New
import java.time.Instant

data class NewDTO(
    @SerializedName("id")
    val id: String,
    @SerializedName("sourceName")
    val sourceName: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("publishedDate")
    val publishedDate: Instant,
) {
    fun toNew(): New {
        return New(
            id = id,
            sourceName = sourceName,
            title = title,
            publishedDate = publishedDate
        )
    }
}