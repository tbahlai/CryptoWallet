package com.tbahlai.cryptowallet.data.repositories

import com.tbahlai.cryptowallet.domain.models.Trending
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrendingRepository @Inject constructor(
) {

    val trendingList: StateFlow<List<Trending>>
        get() = _trendingList
    private val _trendingList: MutableStateFlow<List<Trending>> = MutableStateFlow(emptyList())

    fun getTrendingList(): List<Trending> {
        return _trendingList.value
    }
}