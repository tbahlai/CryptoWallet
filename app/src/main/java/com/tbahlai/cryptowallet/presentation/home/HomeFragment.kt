package com.tbahlai.cryptowallet.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tbahlai.cryptowallet.R
import com.tbahlai.cryptowallet.common.utils.launchWithViewLifecycle
import com.tbahlai.cryptowallet.databinding.FragmentHomeBinding
import com.tbahlai.cryptowallet.presentation.home.HomeFragmentDirections.showCurrencyPageFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by activityViewModels<HomeViewModel>()
    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, group, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(requireActivity(), R.id.homeNavHostFragment)
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
        binding.bottomNavigationView.itemIconTintList = null
        viewModel.observe()
    }

    private fun HomeViewModel.observe() {
        launchWithViewLifecycle {
            collectEvents(::handleEvent)
        }
    }

    private fun handleEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OpenCurrencyPageEvent -> {
                findNavController().navigate(showCurrencyPageFragment(event.id))
            }
        }
    }
}