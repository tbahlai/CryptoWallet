package com.tbahlai.cryptowallet.common.utils

import com.tbahlai.cryptowallet.common.*

fun OnRefreshing.start() : OnRefreshing {
    return this.copy(predicate = true)
}

fun OnLoading.start() : OnLoading {
    return this.copy(predicate = true)
}

fun OnRefreshing.stop() : OnRefreshing {
    return this.copy(predicate = false)
}

fun OnLoading.stop() : OnLoading {
    return this.copy(predicate = false)
}

fun ScreenState.isLoading() : Boolean {
    return if (this is BaseState) this.loading.predicate else false
}

fun ScreenState.isRefreshing() : Boolean {
    return if (this is RefreshState) this.refresh?.predicate ?: false else false
}

fun ScreenState.hasError() : Boolean {
    return if (this is BaseState) this.error?.predicate ?: false else false
}