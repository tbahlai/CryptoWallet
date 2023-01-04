package com.tbahlai.cryptowallet.presentation.home.market

import com.tbahlai.cryptowallet.common.BaseState
import com.tbahlai.cryptowallet.common.OnError
import com.tbahlai.cryptowallet.common.OnLoading
import com.tbahlai.cryptowallet.presentation.home.models.UiMarket

data class MarketState(
    val marketsList: List<UiMarket> = emptyList(),
    override var loading: OnLoading = OnLoading(false),
    override var error: OnError? = null
) : BaseState

sealed class MarketAction {
    data class OnMarketClickedAction(val id: Long) : MarketAction()
}

sealed class MarketEvent {
    data class OnOpenCurrencyPageEvent(val id: Long) : MarketEvent()
}