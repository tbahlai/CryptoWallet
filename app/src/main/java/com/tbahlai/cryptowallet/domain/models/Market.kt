package com.tbahlai.cryptowallet.domain.models

data class Market(
    val id: Long,
    val name: String,
    val logo: Int,
    val description: String,
    val balance: Double,
    val growth: Double,
    val inTrending: Boolean
)
