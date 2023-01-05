package com.tbahlai.cryptowallet.presentation.home.market.currencypage

import com.tbahlai.cryptowallet.common.BaseState
import com.tbahlai.cryptowallet.common.OnError
import com.tbahlai.cryptowallet.common.OnLoading
import com.tbahlai.cryptowallet.presentation.home.models.UiMarket

data class CurrencyPageState(
    val market: UiMarket? = null,
    val btcValue: String = "0",
    val usdValue: String = "0",
    override var loading: OnLoading = OnLoading(false),
    override var error: OnError? = null
) : BaseState

sealed class CurrencyPageAction {
    data class GetCurrencyPageAction(val id: Long) : CurrencyPageAction()
    data class SetBtcValueAction(val value: String) : CurrencyPageAction()
    data class SetUsdValueAction(val value: String) : CurrencyPageAction()
}

sealed class CurrencyPageEvent {

}