package com.tbahlai.cryptowallet.data.repositories

import com.tbahlai.cryptowallet.domain.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
) {

    val currentUserData: StateFlow<User>
        get() = _currentUserData
    private val _currentUserData: MutableStateFlow<User> = MutableStateFlow(User())

    fun getCurrentUserData(): User {
        return _currentUserData.value
    }
}