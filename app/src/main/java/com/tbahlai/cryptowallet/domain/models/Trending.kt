package com.tbahlai.cryptowallet.domain.models

data class Trending(
    val id: Long,
    val name: String,
    val logo: Int,
    val description: String,
    val balance: Double,
    val growth: Double
)
