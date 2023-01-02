package com.tbahlai.cryptowallet.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.tbahlai.cryptowallet.common.utils.hasError
import com.tbahlai.cryptowallet.common.utils.isLoading
import com.tbahlai.cryptowallet.common.utils.isRefreshing
import com.tbahlai.cryptowallet.uikit.components.UiKitEmptyState
import com.tbahlai.cryptowallet.uikit.components.UiKitErrorState
import com.tbahlai.cryptowallet.uikit.components.UiKitProgressIndicator
import com.tbahlai.cryptowallet.uikit.theme.CryptoWalletTheme

abstract class BaseFragment : Fragment() {

    protected abstract val viewModel: BaseViewModel<*, *, *>
    private lateinit var screenState: ScreenState

    @Composable
    protected abstract fun ScaffoldData(content: @Composable () -> Unit)

    @Composable
    protected abstract fun ScreenContent()

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, bundle: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val baseState by viewModel.state.collectAsState()
                screenState = baseState

                CryptoWalletTheme {
                    ScaffoldData {
                        if (screenState is RefreshState) RefreshState() else ScreenContent()
                        LoadingState()
                        ErrorState()
                        EmptyState()
                    }
                }
            }
        }
    }

    @Composable
    fun RefreshState() {
        val refresh = (screenState as RefreshState).refresh
        if (screenState.hasError()) return

        val state = rememberSwipeRefreshState(isRefreshing = refresh?.predicate ?: false)
        SwipeRefresh(state = state, onRefresh = { refresh?.refreshAction?.invoke() }) {
            ScreenContent()
        }
    }

    @Composable
    fun LoadingState() {
        UiKitProgressIndicator(isLoading = screenState.isLoading() && !screenState.isRefreshing())
    }

    @Composable
    fun ErrorState() {
        if (screenState !is BaseState) return
        val error = (screenState as BaseState).error ?: return

        UiKitErrorState(
            context = requireContext(),
            hasError = screenState.hasError() && !screenState.isLoading(),
            retryAction = { error.retryAction() }
        )
    }

    @Composable
    fun EmptyState() {
        if (screenState !is EmptyState) return
        val emptyState = (screenState as EmptyState).empty ?: return

        UiKitEmptyState(
            isEmpty = emptyState.predicate && !screenState.isLoading() && !screenState.hasError(),
            imageResId = emptyState.imageResId,
            message = getString(emptyState.message)
        )
    }

    fun navigateTo(destination: NavDirections) {
        findNavController().navigate(destination)
    }

    fun onBackPressed() {
        findNavController().navigateUp()
    }

}
