package com.tbahlai.cryptowallet.presentation.onboarding.security

import com.tbahlai.cryptowallet.common.BaseState
import com.tbahlai.cryptowallet.common.OnError
import com.tbahlai.cryptowallet.common.OnLoading

data class OnboardingSecurityState(
    override var loading: OnLoading = OnLoading(false),
    override var error: OnError? = null,
) : BaseState

sealed class OnboardingSecurityAction {
    object OnContinueClickedAction: OnboardingSecurityAction()
}

sealed class OnboardingSecurityEvent {
    object OnOpenOnboardingTransformationScreenEvent: OnboardingSecurityEvent()
}