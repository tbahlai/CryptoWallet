package com.tbahlai.cryptowallet.presentation.home.profile

import com.tbahlai.cryptowallet.common.BaseState
import com.tbahlai.cryptowallet.common.OnError
import com.tbahlai.cryptowallet.common.OnLoading
import com.tbahlai.cryptowallet.presentation.home.models.UiNew
import com.tbahlai.cryptowallet.presentation.home.models.UiTrending
import com.tbahlai.cryptowallet.presentation.home.models.UiUser

data class ProfileState(
    val currentUserData: UiUser? = null,
    val trendingList: List<UiTrending> = emptyList(),
    val newsList: List<UiNew> = emptyList(),
    override var loading: OnLoading = OnLoading(false),
    override var error: OnError? = null,
) : BaseState

sealed class ProfileAction {

}

sealed class ProfileEvent {

}