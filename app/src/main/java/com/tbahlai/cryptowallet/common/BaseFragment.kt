package com.tbahlai.cryptowallet.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

abstract class BaseFragment constructor(
    @LayoutRes private val contentLayoutResId: Int = 0,
) : Fragment(contentLayoutResId) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(contentLayoutResId, container, false)
    }

    fun navigateTo(destination: NavDirections) {
        findNavController().navigate(destination)
    }
}