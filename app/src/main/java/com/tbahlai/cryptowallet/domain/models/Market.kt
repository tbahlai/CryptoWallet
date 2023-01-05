package com.tbahlai.cryptowallet.domain.models

data class Market constructor(
    val id: Long,
    val name: String,
    val logo: Int,
    val description: String,
    val balance: Double,
    val growth: Double,
    val lowBalance: Double,
    val highBalance: Double,
    val volume: Double,
    val inTrending: Boolean,
)
