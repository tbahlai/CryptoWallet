package com.tbahlai.cryptowallet.presentation.splash

import com.tbahlai.cryptowallet.common.BaseState
import com.tbahlai.cryptowallet.common.OnError
import com.tbahlai.cryptowallet.common.OnLoading

data class SplashState(
    override var loading: OnLoading = OnLoading(false),
    override var error: OnError? = null,
) : BaseState

sealed class SplashAction {

}

sealed class SplashEvent {
    object OnOpenOnboardingEvent : SplashEvent()
    object OnOpenProfileEvent : SplashEvent()
}