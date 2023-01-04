package com.tbahlai.cryptowallet.common

interface ScreenState

interface BaseState : ScreenState {
    var loading: OnLoading
    var error: OnError?
}

interface RefreshState : ScreenState {
    var refresh: OnRefreshing?
}

interface EmptyState : ScreenState {
    val empty: OnEmpty?
}