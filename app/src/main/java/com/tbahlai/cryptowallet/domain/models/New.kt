package com.tbahlai.cryptowallet.domain.models

import java.time.Instant

data class New(
    val id: String,
    val sourceName: String,
    val title: String,
    val publishedDate: Instant,
)