package com.tbahlai.cryptowallet.presentation.home

import androidx.lifecycle.viewModelScope
import com.tbahlai.cryptowallet.common.BaseViewModel
import com.tbahlai.cryptowallet.presentation.home.HomeAction.OpenCurrencyPageAction
import com.tbahlai.cryptowallet.presentation.home.HomeEvent.OpenCurrencyPageEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(

) : BaseViewModel<HomeState, HomeAction, HomeEvent>() {

    override fun initialState(): HomeState = HomeState()

    public override fun handleAction(action: HomeAction) {
        viewModelScope.launch {
            when (action) {
                is OpenCurrencyPageAction -> sendEvent(OpenCurrencyPageEvent(action.id))
            }
        }
    }
}