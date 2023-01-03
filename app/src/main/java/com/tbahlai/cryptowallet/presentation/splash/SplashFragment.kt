package com.tbahlai.cryptowallet.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tbahlai.cryptowallet.R
import com.tbahlai.cryptowallet.common.utils.launchWithViewLifecycle
import com.tbahlai.cryptowallet.presentation.splash.SplashFragmentDirections.showHomeFragment
import com.tbahlai.cryptowallet.presentation.splash.SplashFragmentDirections.showOnboardingEasinessFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, group, false)
    }

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
            SplashEvent.OnOpenOnboardingEvent -> {
                findNavController().navigate(showOnboardingEasinessFragment())
            }
            SplashEvent.OnOpenProfileEvent -> findNavController().navigate(showHomeFragment())
        }
    }
}