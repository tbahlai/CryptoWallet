package com.tbahlai.cryptowallet.presentation.onboarding.easiness

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
import com.tbahlai.cryptowallet.databinding.FragmentOnboardingEasinessBinding
import com.tbahlai.cryptowallet.presentation.onboarding.easiness.OnboardingEasinessAction.OnContinueClickedAction
import com.tbahlai.cryptowallet.presentation.onboarding.easiness.OnboardingEasinessEvent.OnOpenOnboardingSecurityScreenEvent
import com.tbahlai.cryptowallet.presentation.onboarding.easiness.OnboardingEasinessFragmentDirections.showOnboardingSecurityFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingEasinessFragment : Fragment() {

    private val viewModel by viewModels<OnboardingEasinessViewModel>()
    private val binding by viewBinding(FragmentOnboardingEasinessBinding::bind)

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_onboarding_easiness, group, false)
    }

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
            OnOpenOnboardingSecurityScreenEvent -> {
                findNavController().navigate(showOnboardingSecurityFragment())
            }
        }
    }
}