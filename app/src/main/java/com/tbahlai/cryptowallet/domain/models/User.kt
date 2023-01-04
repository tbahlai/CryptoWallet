package com.tbahlai.cryptowallet.domain.models

data class User(
    val id: Long = 0L,
    val name: String = "Matthew",
    val imageUrl: String = "https://thispersondoesnotexist.com/image",
    val currentBalance: Double = 246.0
)
