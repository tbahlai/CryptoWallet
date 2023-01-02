package com.tbahlai.cryptowallet.presentation.onboarding.transformation

import com.tbahlai.cryptowallet.common.BaseState
import com.tbahlai.cryptowallet.common.OnError
import com.tbahlai.cryptowallet.common.OnLoading

data class OnboardingTransformationState(
    override var loading: OnLoading = OnLoading(false),
    override var error: OnError? = null,
) : BaseState

sealed class OnboardingTransformationAction {
    object OnContinueClickedAction : OnboardingTransformationAction()
}

sealed class OnboardingTransformationEvent {
    object OnOpenProfileScreenEvent: OnboardingTransformationEvent()
}