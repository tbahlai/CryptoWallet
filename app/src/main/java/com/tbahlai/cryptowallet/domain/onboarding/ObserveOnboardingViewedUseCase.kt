package com.tbahlai.cryptowallet.domain.onboarding

import com.tbahlai.cryptowallet.data.repositories.OnboardingRepository
import com.tbahlai.cryptowallet.data.repositories.OnboardingState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveOnboardingViewedUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository,
) {
    operator fun invoke(): Flow<OnboardingState> {
        return onboardingRepository.onboardingStateFlow
    }
}