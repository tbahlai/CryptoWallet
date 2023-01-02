package com.tbahlai.cryptowallet.presentation.onboarding.security

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tbahlai.cryptowallet.R
import com.tbahlai.cryptowallet.common.utils.launchWithViewLifecycle
import com.tbahlai.cryptowallet.databinding.FragmentOnboardingSecurityBinding
import com.tbahlai.cryptowallet.presentation.onboarding.security.OnboardingSecurityEvent.OnOpenOnboardingTransformationScreenEvent
import com.tbahlai.cryptowallet.presentation.onboarding.security.OnboardingSecurityFragmentDirections.showOnboardingTransformationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingSecurityFragment : Fragment() {

    private val viewModel by viewModels<OnboardingSecurityViewModel>()
    private val binding by viewBinding(FragmentOnboardingSecurityBinding::bind)

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_onboarding_security, group, false)
    }

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
                findNavController().navigate(showOnboardingTransformationFragment())
            }
        }
    }
}