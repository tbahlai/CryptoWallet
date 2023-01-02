package com.tbahlai.cryptowallet.presentation.onboarding.transformation

import com.tbahlai.cryptowallet.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingTransformationViewModel @Inject constructor(

) : BaseViewModel<OnboardingTransformationState, OnboardingTransformationAction, OnboardingTransformationEvent>() {

    override fun initialState(): OnboardingTransformationState = OnboardingTransformationState()

    public override fun handleAction(action: OnboardingTransformationAction) {

    }
}