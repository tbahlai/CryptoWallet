package com.tbahlai.cryptowallet.presentation.home.market.currencypage

import androidx.lifecycle.viewModelScope
import com.tbahlai.cryptowallet.common.BaseViewModel
import com.tbahlai.cryptowallet.domain.market.GetMarketListUseCase
import com.tbahlai.cryptowallet.presentation.home.mappers.MarketToUiMarketMapper
import com.tbahlai.cryptowallet.presentation.home.market.currencypage.CurrencyPageAction.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyPageViewModel @Inject constructor(
    private val getMarketListUseCase: GetMarketListUseCase,
    private val marketToUiMarketMapper: MarketToUiMarketMapper
) : BaseViewModel<CurrencyPageState, CurrencyPageAction, CurrencyPageEvent>() {

    override fun initialState(): CurrencyPageState = CurrencyPageState()

    public override fun handleAction(action: CurrencyPageAction) {
        when (action) {
            is GetCurrencyPageAction -> getCurrencyPageBy(action.id)
            is SetBtcValueAction -> setData { copy(btcValue = action.value) }
            is SetUsdValueAction -> setData { copy(usdValue = action.value) }
        }
    }

    private fun getCurrencyPageBy(id: Long) {
        viewModelScope.launch {
            val marketMap = getMarketListUseCase().associateBy { it.id }
            marketMap[id]?.let { market ->
                setData { copy(market = marketToUiMarketMapper.map(market)) }
            }
        }
    }
}