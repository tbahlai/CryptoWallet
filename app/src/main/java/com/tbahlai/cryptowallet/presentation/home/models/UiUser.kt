package com.tbahlai.cryptowallet.presentation.home.models

data class UiUser(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val currentBalance: Double
)