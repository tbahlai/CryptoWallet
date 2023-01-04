package com.tbahlai.cryptowallet.domain.market

import com.tbahlai.cryptowallet.domain.models.Market
import javax.inject.Inject

class GetTrendingMarketsListUseCase @Inject constructor(
    private val getMarketListUseCase: GetMarketListUseCase
) {

    operator fun invoke() : List<Market> {
        return getMarketListUseCase().filter { it.inTrending }
    }
}