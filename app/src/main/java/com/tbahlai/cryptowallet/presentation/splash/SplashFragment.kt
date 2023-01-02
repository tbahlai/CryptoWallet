package com.tbahlai.cryptowallet.presentation.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.tbahlai.cryptowallet.R
import com.tbahlai.cryptowallet.common.BaseFragment
import com.tbahlai.cryptowallet.common.utils.launchWithViewLifecycle
import com.tbahlai.cryptowallet.presentation.splash.SplashFragmentDirections.showOnboardingEasinessFragment

class SplashFragment : BaseFragment(R.layout.fragment_splash) {

    private val viewModel by viewModels<SplashViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observe()
    }

    private fun SplashViewModel.observe() {
        launchWithViewLifecycle {
            collectEvents(::handleEvent)
        }
    }

    private fun handleEvent(splashEvent: SplashEvent) {
        when (splashEvent) {
            SplashEvent.OnOpenOnboardingEvent -> navigateTo(showOnboardingEasinessFragment())
        }
    }
}