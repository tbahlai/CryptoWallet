package com.tbahlai.cryptowallet.presentation.onboarding.security

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tbahlai.cryptowallet.R
import com.tbahlai.cryptowallet.common.BaseFragment
import com.tbahlai.cryptowallet.common.utils.launchWithViewLifecycle
import com.tbahlai.cryptowallet.databinding.FragmentOnboardingSecurityBinding
import com.tbahlai.cryptowallet.presentation.onboarding.security.OnboardingSecurityEvent.OnOpenOnboardingTransformationScreenEvent
import com.tbahlai.cryptowallet.presentation.onboarding.security.OnboardingSecurityFragmentDirections.showOnboardingTransformationFragment

class OnboardingSecurityFragment : BaseFragment(R.layout.fragment_onboarding_security) {

    private val viewModel by viewModels<OnboardingSecurityViewModel>()
    private val binding by viewBinding(FragmentOnboardingSecurityBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.securityContinue.setOnClickListener {
            viewModel.handleAction(OnboardingSecurityAction.OnContinueClickedAction)
        }
        viewModel.observe()
    }

    private fun OnboardingSecurityViewModel.observe() {
        launchWithViewLifecycle {
            collectEvents(::handleEvent)
        }
    }

    private fun handleEvent(securityEvent: OnboardingSecurityEvent) {
        when (securityEvent) {
            OnOpenOnboardingTransformationScreenEvent -> {
                navigateTo(showOnboardingTransformationFragment())
            }
        }
    }
}