package com.tbahlai.cryptowallet.presentation.onboarding.transformation

import androidx.lifecycle.viewModelScope
import com.tbahlai.cryptowallet.common.BaseViewModel
import com.tbahlai.cryptowallet.domain.onboarding.SaveOnboardingViewedUseCase
import com.tbahlai.cryptowallet.presentation.onboarding.transformation.OnboardingTransformationAction.OnContinueClickedAction
import com.tbahlai.cryptowallet.presentation.onboarding.transformation.OnboardingTransformationEvent.OnOpenProfileScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingTransformationViewModel @Inject constructor(
    private val saveOnboardingViewedUseCase: SaveOnboardingViewedUseCase
) : BaseViewModel<OnboardingTransformationState, OnboardingTransformationAction, OnboardingTransformationEvent>() {

    override fun initialState(): OnboardingTransformationState = OnboardingTransformationState()

    public override fun handleAction(action: OnboardingTransformationAction) {
        viewModelScope.launch {
            when (action) {
                OnContinueClickedAction -> {
                    saveOnboardingViewedUseCase()
                    sendEvent(OnOpenProfileScreenEvent)
                }
            }
        }
    }
}