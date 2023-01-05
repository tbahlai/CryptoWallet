package com.tbahlai.cryptowallet.presentation.home.mappers

import com.tbahlai.cryptowallet.domain.models.Market
import com.tbahlai.cryptowallet.presentation.home.models.UiMarket
import javax.inject.Inject

class MarketToUiMarketMapper @Inject constructor(

) {

    fun map(market: Market) : UiMarket {
        return UiMarket(
            id = market.id,
            name = market.name,
            logo = market.logo,
            description = market.description,
            balance = market.balance,
            growth = market.growth,
            lowBalance = market.lowBalance,
            highBalance = market.highBalance,
            volume = market.volume
        )
    }
}