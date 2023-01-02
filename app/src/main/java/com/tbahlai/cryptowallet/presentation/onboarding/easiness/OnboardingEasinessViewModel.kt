package com.tbahlai.cryptowallet.presentation.onboarding.easiness

import androidx.lifecycle.viewModelScope
import com.tbahlai.cryptowallet.common.BaseViewModel
import com.tbahlai.cryptowallet.presentation.onboarding.easiness.OnboardingEasinessAction.OnContinueClickedAction
import com.tbahlai.cryptowallet.presentation.onboarding.easiness.OnboardingEasinessEvent.OnOpenOnboardingSecurityScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingEasinessViewModel @Inject constructor(

) : BaseViewModel<OnboardingEasinessState, OnboardingEasinessAction, OnboardingEasinessEvent>() {

    override fun initialState(): OnboardingEasinessState = OnboardingEasinessState()

    public override fun handleAction(action: OnboardingEasinessAction) {
        viewModelScope.launch {
            when (action) {
                OnContinueClickedAction -> sendEvent(OnOpenOnboardingSecurityScreenEvent)
            }
        }
    }
}