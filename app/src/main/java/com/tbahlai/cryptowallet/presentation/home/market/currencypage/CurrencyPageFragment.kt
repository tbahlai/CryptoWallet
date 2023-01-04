package com.tbahlai.cryptowallet.presentation.home.market.currencypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tbahlai.cryptowallet.R

class CurrencyPageFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_currency_page, group, false)
    }
}