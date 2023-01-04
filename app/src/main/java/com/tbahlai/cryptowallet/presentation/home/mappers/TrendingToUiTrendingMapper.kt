package com.tbahlai.cryptowallet.presentation.home.mappers

import com.tbahlai.cryptowallet.domain.models.Trending
import com.tbahlai.cryptowallet.presentation.home.models.UiTrending
import javax.inject.Inject

class TrendingToUiTrendingMapper @Inject constructor(

) {

    fun map(trending: Trending) : UiTrending {
        return UiTrending(
            id = trending.id,
            name = trending.name,
            logo = trending.logo,
            description = trending.description,
            balance = trending.balance,
            growth = trending.growth
        )
    }
}