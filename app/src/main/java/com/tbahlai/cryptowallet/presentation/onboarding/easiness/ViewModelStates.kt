package com.tbahlai.cryptowallet.presentation.onboarding.easiness

import com.tbahlai.cryptowallet.common.BaseState
import com.tbahlai.cryptowallet.common.OnError
import com.tbahlai.cryptowallet.common.OnLoading

data class OnboardingEasinessState(
    override var loading: OnLoading = OnLoading(false),
    override var error: OnError? = null,
) : BaseState

sealed class OnboardingEasinessAction {
    object OnContinueClickedAction : OnboardingEasinessAction()
}

sealed class OnboardingEasinessEvent {
    object OnOpenOnboardingSecurityScreenEvent : OnboardingEasinessEvent()
}