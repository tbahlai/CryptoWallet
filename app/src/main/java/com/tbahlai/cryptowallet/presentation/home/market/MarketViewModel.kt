package com.tbahlai.cryptowallet.presentation.home.market

import androidx.lifecycle.viewModelScope
import com.tbahlai.cryptowallet.common.BaseViewModel
import com.tbahlai.cryptowallet.domain.market.GetMarketListUseCase
import com.tbahlai.cryptowallet.presentation.home.mappers.MarketToUiMarketMapper
import com.tbahlai.cryptowallet.presentation.home.market.MarketAction.OnMarketClickedAction
import com.tbahlai.cryptowallet.presentation.home.market.MarketEvent.OnOpenCurrencyPageEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarketViewModel @Inject constructor(
    private val getMarketListUseCase: GetMarketListUseCase,
    private val marketToUiMarketMapper: MarketToUiMarketMapper
): BaseViewModel<MarketState, MarketAction, MarketEvent>() {

    init {
        getMarketsList()
    }

    private fun getMarketsList() {
        val marketsList = getMarketListUseCase().map(marketToUiMarketMapper::map)
        setData { copy(marketsList = marketsList) }
    }

    override fun initialState(): MarketState = MarketState()

    public override fun handleAction(action: MarketAction) {
        viewModelScope.launch {
            when (action) {
                is OnMarketClickedAction -> sendEvent(OnOpenCurrencyPageEvent(action.id))
            }
        }
    }
}