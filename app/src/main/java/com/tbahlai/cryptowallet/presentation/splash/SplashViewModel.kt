package com.tbahlai.cryptowallet.presentation.splash

import androidx.lifecycle.viewModelScope
import com.tbahlai.cryptowallet.common.BaseViewModel
import com.tbahlai.cryptowallet.data.repositories.OnboardingState.NotViewedState
import com.tbahlai.cryptowallet.data.repositories.OnboardingState.ViewedState
import com.tbahlai.cryptowallet.domain.onboarding.ObserveOnboardingViewedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val observeOnboardingViewedUseCase: ObserveOnboardingViewedUseCase,
) : BaseViewModel<SplashState, SplashAction, SplashEvent>() {

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            observeOnboardingViewedUseCase()
                .map { onboardingState ->
                    when (onboardingState) {
                        NotViewedState -> sendEvent(SplashEvent.OnOpenOnboardingEvent)
                        ViewedState -> sendEvent(SplashEvent.OnOpenProfileEvent)
                    }
                }
                .launchIn(viewModelScope)
        }
    }

    override fun initialState(): SplashState = SplashState()

    public override fun handleAction(action: SplashAction) {
    }
}