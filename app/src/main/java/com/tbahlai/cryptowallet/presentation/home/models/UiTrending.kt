package com.tbahlai.cryptowallet.presentation.home.models

data class UiTrending(
    val id: Long,
    val name: String,
    val logo: Int,
    val description: String,
    val balance: Double,
    val growth: Double
) {
    val isGrowthUp: Boolean
        get() = growth > 0
}
