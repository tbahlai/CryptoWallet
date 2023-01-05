package com.tbahlai.cryptowallet.presentation.home.models

data class UiMarket constructor(
    val id: Long,
    val name: String,
    val logo: Int,
    val description: String,
    val balance: Double,
    val lowBalance: Double,
    val highBalance: Double,
    val volume: Double,
    val growth: Double
) {
    val isGrowthUp: Boolean
        get() = growth > 0
}
