package com.tbahlai.cryptowallet.presentation.onboarding.transformation

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
import com.tbahlai.cryptowallet.databinding.FragmentOnboardingTransformationBinding
import com.tbahlai.cryptowallet.presentation.onboarding.transformation.OnboardingTransformationAction.OnContinueClickedAction
import com.tbahlai.cryptowallet.presentation.onboarding.transformation.OnboardingTransformationEvent.OnOpenProfileScreenEvent
import com.tbahlai.cryptowallet.presentation.onboarding.transformation.OnboardingTransformationFragmentDirections.showProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingTransformationFragment : Fragment() {

    private val viewModel by viewModels<OnboardingTransformationViewModel>()
    private val binding by viewBinding(FragmentOnboardingTransformationBinding::bind)

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_onboarding_transformation, group, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.transformationContinue.setOnClickListener {
            viewModel.handleAction(OnContinueClickedAction)
        }
        viewModel.observe()
    }

    private fun OnboardingTransformationViewModel.observe() {
        launchWithViewLifecycle {
            collectEvents(::handleEvent)
        }
    }

    private fun handleEvent(transformationEvent: OnboardingTransformationEvent) {
        when (transformationEvent) {
            OnOpenProfileScreenEvent -> findNavController().navigate(showProfileFragment())
        }
    }
}