package com.tbahlai.cryptowallet.domain.onboarding

import com.tbahlai.cryptowallet.data.repositories.OnboardingRepository
import javax.inject.Inject

class SaveOnboardingViewedUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository,
) {

    operator fun invoke() {
        onboardingRepository.saveOnboardingViewed()
    }
}