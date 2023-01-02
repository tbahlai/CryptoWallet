package com.tbahlai.cryptowallet.data.repositories

import android.app.Application
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV
import androidx.security.crypto.EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
import androidx.security.crypto.MasterKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnboardingRepository @Inject constructor(
    private val context: Application,
) {

    private val isViewed: Boolean
        get() = sharedPreferences.getBoolean(IS_VIEWED, true)

    private val sharedPreferences by lazy {
        context.let {
            val masterKey = MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()

            EncryptedSharedPreferences
                .create(context, SHARED_PREFERENCES_NAME, masterKey, AES256_SIV, AES256_GCM)
        }
    }

    val onboardingStateFlow: StateFlow<OnboardingState>
        get() = _onboardingStateFlow
    private val _onboardingStateFlow = MutableStateFlow(init())

    private fun init(): OnboardingState {
        return when (sharedPreferences.contains(IS_VIEWED) && isViewed) {
            true -> OnboardingState.ViewedState
            false -> OnboardingState.NotViewedState
        }
    }

    fun saveOnboardingViewed() {
        _onboardingStateFlow.value = OnboardingState.ViewedState
        with(sharedPreferences.edit()) {
            putBoolean(IS_VIEWED, true)
            commit()
        }
    }

    private companion object {
        private const val SHARED_PREFERENCES_NAME = "CRYPTO_WALLET_PREFERENCES"
        private const val IS_VIEWED = "IS_VIEWED"
    }
}

sealed class OnboardingState {
    object ViewedState : OnboardingState()
    object NotViewedState : OnboardingState()
}