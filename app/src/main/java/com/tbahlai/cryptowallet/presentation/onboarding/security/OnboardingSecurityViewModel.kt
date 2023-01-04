package com.tbahlai.cryptowallet.presentation.onboarding.security

import androidx.lifecycle.viewModelScope
import com.tbahlai.cryptowallet.common.BaseViewModel
import com.tbahlai.cryptowallet.presentation.onboarding.security.OnboardingSecurityEvent.OnOpenOnboardingTransformationScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingSecurityViewModel @Inject constructor(

) : BaseViewModel<OnboardingSecurityState, OnboardingSecurityAction, OnboardingSecurityEvent>() {

    override fun initialState(): OnboardingSecurityState = OnboardingSecurityState()

    public override fun handleAction(action: OnboardingSecurityAction) {
        viewModelScope.launch {
            when (action) {
                OnboardingSecurityAction.OnContinueClickedAction -> {
                    sendEvent(OnOpenOnboardingTransformationScreenEvent)
                }
            }
        }
    }
}