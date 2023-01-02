package com.tbahlai.cryptowallet.presentation.splash

import androidx.lifecycle.viewModelScope
import com.tbahlai.cryptowallet.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(

) : BaseViewModel<SplashState, SplashAction, SplashEvent>() {

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            sendEvent(SplashEvent.OnOpenOnboardingEvent)
        }
    }

    override fun initialState(): SplashState = SplashState()

    public override fun handleAction(action: SplashAction) {
    }
}