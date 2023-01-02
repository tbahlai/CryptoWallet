package com.tbahlai.cryptowallet.presentation.onboarding.easiness

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tbahlai.cryptowallet.R
import com.tbahlai.cryptowallet.common.BaseFragment
import com.tbahlai.cryptowallet.common.utils.launchWithViewLifecycle
import com.tbahlai.cryptowallet.databinding.FragmentOnboardingEasinessBinding
import com.tbahlai.cryptowallet.presentation.onboarding.easiness.OnboardingEasinessAction.OnContinueClickedAction
import com.tbahlai.cryptowallet.presentation.onboarding.easiness.OnboardingEasinessEvent.OnOpenOnboardingSecurityScreenEvent
import com.tbahlai.cryptowallet.presentation.onboarding.easiness.OnboardingEasinessFragmentDirections.showOnboardingSecurityFragment

class OnboardingEasinessFragment : BaseFragment(R.layout.fragment_onboarding_easiness) {

    private val viewModel by viewModels<OnboardingEasinessViewModel>()
    private val binding by viewBinding(FragmentOnboardingEasinessBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.easinessContinue.setOnClickListener {
            viewModel.handleAction(OnContinueClickedAction)
        }
        viewModel.observe()
    }

    private fun OnboardingEasinessViewModel.observe() {
        launchWithViewLifecycle {
            collectEvents(::handleEvent)
        }
    }

    private fun handleEvent(easinessEvent: OnboardingEasinessEvent) {
        when (easinessEvent) {
            OnOpenOnboardingSecurityScreenEvent -> navigateTo(showOnboardingSecurityFragment())
        }
    }
}