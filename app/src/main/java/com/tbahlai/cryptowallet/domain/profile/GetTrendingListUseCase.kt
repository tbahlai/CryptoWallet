package com.tbahlai.cryptowallet.domain.profile

import com.tbahlai.cryptowallet.R
import com.tbahlai.cryptowallet.data.repositories.TrendingRepository
import com.tbahlai.cryptowallet.domain.models.Trending
import javax.inject.Inject

class GetTrendingListUseCase @Inject constructor(
    private val trendingRepository: TrendingRepository
) {

    operator fun invoke() : List<Trending> {
        return listOf(
            /**
             * This is a temporary solution. It should be removed when the Api Call is added.
             */
            Trending(
                id = 0L,
                name = "Binance Coin",
                description = "BNB",
                logo = R.drawable.ic_bitcoin_logo,
                balance = 352.20,
                growth = -0.27,
            ),
            Trending(
                id = 1L,
                name = "Cardano",
                description = "ADA",
                logo = R.drawable.ic_cardano_logo,
                balance = 2936136.20,
                growth = 4.11,
            )
        )
//        return trendingRepository.getTrendingList()
    }
}