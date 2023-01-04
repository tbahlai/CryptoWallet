package com.tbahlai.cryptowallet.presentation.home

import com.tbahlai.cryptowallet.common.BaseState
import com.tbahlai.cryptowallet.common.OnError
import com.tbahlai.cryptowallet.common.OnLoading

data class HomeState(
    override var loading: OnLoading = OnLoading(false),
    override var error: OnError? = null
) : BaseState

sealed class HomeAction {
    data class OpenCurrencyPageAction(val id: Long) : HomeAction()
}

sealed class HomeEvent {
    data class OpenCurrencyPageEvent(val id: Long) : HomeEvent()
}