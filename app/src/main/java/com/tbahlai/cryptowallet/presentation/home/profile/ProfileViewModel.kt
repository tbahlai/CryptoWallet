package com.tbahlai.cryptowallet.presentation.home.profile

import androidx.lifecycle.viewModelScope
import com.tbahlai.cryptowallet.common.BaseViewModel
import com.tbahlai.cryptowallet.common.OnError
import com.tbahlai.cryptowallet.common.utils.start
import com.tbahlai.cryptowallet.domain.market.GetTrendingMarketsListUseCase
import com.tbahlai.cryptowallet.domain.news.GetNewsListUseCase
import com.tbahlai.cryptowallet.domain.profile.GetCurrentUserDataUseCase
import com.tbahlai.cryptowallet.presentation.home.mappers.MarketToUiMarketMapper
import com.tbahlai.cryptowallet.presentation.home.mappers.NewToUiNewMapper
import com.tbahlai.cryptowallet.presentation.home.mappers.UserToUiUserMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userToUiUserMapper: UserToUiUserMapper,
    private val getCurrentUserDataUseCase: GetCurrentUserDataUseCase,
    private val marketToUiMarketMapper: MarketToUiMarketMapper,
    private val getTrendingMarketsListUseCase: GetTrendingMarketsListUseCase,
    private val getNewsListUseCase: GetNewsListUseCase,
    private val newToUiNewMapper: NewToUiNewMapper
) : BaseViewModel<ProfileState, ProfileAction, ProfileEvent>() {

    init {
        getCurrentUserData()
        getMarketsList()
        getNewsList()
    }

    private fun getCurrentUserData() {
        viewModelScope.launch {
            val user = getCurrentUserDataUseCase()
            setData { copy(currentUserData = userToUiUserMapper.map(user)) }
        }
    }

    private fun getMarketsList() {
        viewModelScope.launch {
            val marketsList = getTrendingMarketsListUseCase().map(marketToUiMarketMapper::map)
            setData { copy(marketsList = marketsList) }
        }
    }

    private fun getNewsList() {
        updateState { copy(loading = loading.start()) }
        viewModelScope.launch {
            getNewsListUseCase()
                .onSuccess { setData { copy(newsList = it.map(newToUiNewMapper::map)) } }
                .onFailure {
                    val error = OnError(predicate = true, retryAction = { getNewsList() })
                    setError { copy(error = error) }
                }
        }
    }

    override fun initialState(): ProfileState = ProfileState()

    public override fun handleAction(action: ProfileAction) {
    }
}