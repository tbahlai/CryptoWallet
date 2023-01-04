package com.tbahlai.cryptowallet.domain.market

import com.tbahlai.cryptowallet.R
import com.tbahlai.cryptowallet.domain.models.Market
import javax.inject.Inject

class GetMarketListUseCase @Inject constructor() {

    operator fun invoke() : List<Market> {
        return listOf(
            /**
             * This is a temporary solution. It should be removed when the Api Call is added.
             */
            Market(
                id = 0L,
                name = "Etherium",
                description = "ETH",
                logo = R.drawable.ic_etherium,
                balance = 2678.95,
                growth = 4.95,
                inTrending = false
            ),
            Market(
                id = 1L,
                name = "Ethereum 2",
                description = "ETH2",
                logo = R.drawable.ic_etherium2,
                balance = 2383.65,
                growth = 4.91,
                inTrending = false
            ),
            Market(
                id = 2L,
                name = "Tether",
                description = "USDT",
                logo = R.drawable.ic_tether,
                balance = 2.00,
                growth = -0.05,
                inTrending = false
            ),
            Market(
                id = 3L,
                name = "Binance Coin",
                description = "BNB",
                logo = R.drawable.ic_bitcoin_logo,
                balance = 352.20,
                growth = -0.27,
                inTrending = true
            ),
            Market(
                id = 4L,
                name = "Cardano",
                description = "ADA",
                logo = R.drawable.ic_cardano_logo,
                balance = 2936136.20,
                growth = 4.11,
                inTrending = true
            ),
            Market(
                id = 5L,
                name = "Chain link",
                description = "LINK",
                logo = R.drawable.ic_chain_link,
                balance = 2836137.20,
                growth = 2.23,
                inTrending = false
            ),
            Market(
                id = 6L,
                name = "Bitcoin",
                description = "BTC",
                logo = R.drawable.ic_bitcoin_market,
                balance = 36751.20,
                growth = 2.23,
                inTrending = false
            )
        )
    }
}