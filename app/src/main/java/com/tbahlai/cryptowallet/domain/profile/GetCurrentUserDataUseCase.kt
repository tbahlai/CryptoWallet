package com.tbahlai.cryptowallet.domain.profile

import com.tbahlai.cryptowallet.data.repositories.UserRepository
import com.tbahlai.cryptowallet.domain.models.User
import javax.inject.Inject

class GetCurrentUserDataUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    operator fun invoke(): User {
        return userRepository.getCurrentUserData()
    }
}