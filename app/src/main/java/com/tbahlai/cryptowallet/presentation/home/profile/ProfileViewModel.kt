package com.tbahlai.cryptowallet.presentation.home.profile

import androidx.lifecycle.viewModelScope
import com.tbahlai.cryptowallet.common.BaseViewModel
import com.tbahlai.cryptowallet.common.OnError
import com.tbahlai.cryptowallet.common.utils.start
import com.tbahlai.cryptowallet.domain.news.GetNewsListUseCase
import com.tbahlai.cryptowallet.domain.profile.GetCurrentUserDataUseCase
import com.tbahlai.cryptowallet.domain.profile.GetTrendingListUseCase
import com.tbahlai.cryptowallet.presentation.home.mappers.NewToUiNewMapper
import com.tbahlai.cryptowallet.presentation.home.mappers.TrendingToUiTrendingMapper
import com.tbahlai.cryptowallet.presentation.home.mappers.UserToUiUserMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userToUiUserMapper: UserToUiUserMapper,
    private val getCurrentUserDataUseCase: GetCurrentUserDataUseCase,
    private val trendingToUiTrendingMapper: TrendingToUiTrendingMapper,
    private val getTrendingListUseCase: GetTrendingListUseCase,
    private val getNewsListUseCase: GetNewsListUseCase,
    private val newToUiNewMapper: NewToUiNewMapper
) : BaseViewModel<ProfileState, ProfileAction, ProfileEvent>() {

    init {
        getCurrentUserData()
        getTrendingList()
        getNewsList()
    }

    private fun getCurrentUserData() {
        viewModelScope.launch {
            val user = getCurrentUserDataUseCase()
            setData { copy(currentUserData = userToUiUserMapper.map(user)) }
        }
    }

    private fun getTrendingList() {
        viewModelScope.launch {
            val trendingList = getTrendingListUseCase().map(trendingToUiTrendingMapper::map)
            setData { copy(trendingList = trendingList) }
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