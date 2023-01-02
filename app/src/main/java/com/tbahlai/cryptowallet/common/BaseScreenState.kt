package com.tbahlai.cryptowallet.common

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.tbahlai.cryptowallet.R

sealed class BaseScreenState {
    abstract val predicate: Boolean
}

data class OnLoading(
    override val predicate: Boolean
) : BaseScreenState()

data class OnRefreshing(
    override val predicate: Boolean = false,
    val refreshAction: () -> Unit
): BaseScreenState()

data class OnEmpty(
    override val predicate: Boolean,
    @DrawableRes val imageResId: Int = R.drawable.ic_empty_search,
    @StringRes val message: Int
) : BaseScreenState()

data class OnError(
    override val predicate: Boolean,
    @DrawableRes val imageResId: Int = R.drawable.ic_something_broken,
    @StringRes val titleResId: Int = R.string.something_broken_title,
    @StringRes val messageResId: Int = R.string.something_broken_message,
    val retryAction: () -> Unit
) : BaseScreenState()