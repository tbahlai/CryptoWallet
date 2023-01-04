package com.tbahlai.cryptowallet.presentation.home.models

import java.time.Instant

data class UiNew(
    val id: String,
    val sourceName: String,
    val title: String,
    val publishedDate: Instant,
)