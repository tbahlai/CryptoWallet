package com.tbahlai.cryptowallet.presentation.home.mappers

import com.tbahlai.cryptowallet.domain.models.User
import com.tbahlai.cryptowallet.presentation.home.models.UiUser
import javax.inject.Inject

class UserToUiUserMapper @Inject constructor(
) {
    fun map(user: User) : UiUser {
        return UiUser(
            id = user.id,
            name = user.name,
            imageUrl = user.imageUrl,
            currentBalance = user.currentBalance
        )
    }
}