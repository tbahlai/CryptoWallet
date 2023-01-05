package com.tbahlai.cryptowallet.presentation.home.market

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.tbahlai.cryptowallet.common.BaseComposeFragment
import com.tbahlai.cryptowallet.common.utils.collectAsEffect
import com.tbahlai.cryptowallet.presentation.home.HomeAction.OpenCurrencyPageAction
import com.tbahlai.cryptowallet.presentation.home.HomeViewModel
import com.tbahlai.cryptowallet.presentation.home.market.MarketAction.OnMarketClickedAction
import com.tbahlai.cryptowallet.uikit.components.UiKitScaffold
import com.tbahlai.cryptowallet.uikit.theme.DarkGreen
import com.tbahlai.cryptowallet.uikit.theme.DarkRed
import com.tbahlai.cryptowallet.uikit.theme.TypographyGray
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarketFragment : BaseComposeFragment() {

    override val viewModel by viewModels<MarketViewModel>()
    private val homeViewModel by activityViewModels<HomeViewModel>(
    )

    @Composable
    override fun ScaffoldData(content: @Composable () -> Unit) {
        UiKitScaffold { content() }
        HandleEvents()
    }

    @Composable
    override fun ScreenContent() {
        val state by viewModel.state.collectAsState()

        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 24.dp)
        ) {
            state.marketsList.forEach { market ->
                ConstraintLayout(
                    modifier = Modifier
                        .clickable { viewModel.handleAction(OnMarketClickedAction(market.id)) }
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    val (image, title, description, balance, growth) = createRefs()
                    createVerticalChain(title, description, chainStyle = ChainStyle.Packed)
                    createVerticalChain(balance, growth, chainStyle = ChainStyle.Packed)

                    Image(
                        modifier = Modifier
                            .size(46.dp)
                            .constrainAs(image) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                            },
                        painter = painterResource(id = market.logo),
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier
                            .constrainAs(title) {
                                top.linkTo(image.top)
                                start.linkTo(image.end, 12.dp)
                                bottom.linkTo(description.top)
                            },
                        text = market.name,
                        style = MaterialTheme.typography.body1
                    )
                    Text(
                        modifier = Modifier
                            .constrainAs(description) {
                                bottom.linkTo(image.bottom)
                                start.linkTo(image.end, 12.dp)
                                top.linkTo(title.bottom)
                            }
                            .padding(top = 4.dp),
                        text = market.description,
                        style = MaterialTheme.typography.caption,
                        color = TypographyGray
                    )
                    Text(
                        modifier = Modifier
                            .constrainAs(balance) {
                                top.linkTo(parent.top)
                                end.linkTo(parent.end)
                                bottom.linkTo(growth.top)
                            },
                        text = "$${market.balance}",
                        color = TypographyGray,
                        style = MaterialTheme.typography.body2
                    )

                    val sign = if (market.isGrowthUp) "+" else ""
                    Text(
                        modifier = Modifier
                            .constrainAs(growth) {
                                bottom.linkTo(parent.bottom)
                                end.linkTo(parent.end)
                                top.linkTo(balance.bottom)
                            }
                            .padding(top = 4.dp),
                        text = "${sign}${market.growth}%",
                        color = if (market.isGrowthUp) DarkGreen else DarkRed,
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }
    }

    @Composable
    private fun HandleEvents() {
        viewModel.events.collectAsEffect { event ->
            when (event) {
                is MarketEvent.OnOpenCurrencyPageEvent -> {
                    homeViewModel.handleAction(OpenCurrencyPageAction(event.id))
                }
            }
        }
    }
}