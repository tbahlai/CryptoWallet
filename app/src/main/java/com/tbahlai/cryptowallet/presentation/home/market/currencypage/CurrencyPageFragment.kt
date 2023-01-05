package com.tbahlai.cryptowallet.presentation.home.market.currencypage

import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.tbahlai.cryptowallet.R
import com.tbahlai.cryptowallet.common.BaseComposeFragment
import com.tbahlai.cryptowallet.presentation.home.market.currencypage.CurrencyPageAction.*
import com.tbahlai.cryptowallet.presentation.home.models.UiMarket
import com.tbahlai.cryptowallet.uikit.components.*
import com.tbahlai.cryptowallet.uikit.theme.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyPageFragment : BaseComposeFragment() {

    private val args by navArgs<CurrencyPageFragmentArgs>()
    override val viewModel by viewModels<CurrencyPageViewModel>()

    @Composable
    override fun ScaffoldData(content: @Composable () -> Unit) {
        SetStatusBarColor(DarkBlue)
        UiKitScaffold { content() }
    }

    @OptIn(ExperimentalTextApi::class)
    @Composable
    override fun ScreenContent() {
        viewModel.handleAction(GetCurrencyPageAction(args.marketId))

        val state by viewModel.state.collectAsState()
        val market = state.market ?: return

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 24.dp)
        ) {
            val (header, textFields, buyBtcButton, sellBtcButton, aboutBtcTitle,
                textStubAboutBtc, showMore, infoItems) = createRefs()

            DrawHeader(
                modifier = Modifier.constrainAs(header) { top.linkTo(parent.top) },
                market = market
            )
            SetTextFields(
                modifier = Modifier
                    .constrainAs(textFields) {
                        top.linkTo(header.bottom, 32.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                state = state
            )
            UiKitButton(
                Modifier
                    .constrainAs(buyBtcButton) {
                        top.linkTo(textFields.bottom, 24.dp)
                        start.linkTo(parent.start, 20.dp)
                        end.linkTo(sellBtcButton.start, 5.dp)
                        width = Dimension.fillToConstraints
                    },
                text = stringResource(R.string.buy_btc),
                description = "1 067 620 USD ≈ 5 BTC"
            ) { }

            UiKitButton(
                Modifier
                    .constrainAs(sellBtcButton) {
                        top.linkTo(textFields.bottom, 24.dp)
                        end.linkTo(parent.end, 20.dp)
                        start.linkTo(buyBtcButton.end, 5.dp)
                        width = Dimension.fillToConstraints
                    },
                text = stringResource(R.string.sell_btc),
                description = "5 BTC ≈ 86 672.7 USD"
            ) { }

            UiKitTitle(
                modifier = Modifier
                    .constrainAs(aboutBtcTitle) {
                        top.linkTo(sellBtcButton.bottom, 44.dp)
                        start.linkTo(parent.start, 20.dp)
                    },
                textResId = R.string.about_btc,
                drawableResId = R.drawable.ic_about_btc
            )
            Text(
                modifier = Modifier
                    .constrainAs(textStubAboutBtc) {
                        top.linkTo(aboutBtcTitle.bottom, 18.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(horizontal = 20.dp),
                text = stringResource(id = R.string.text_stub_about_btc),
                style = TextStyle(
                    brush = Brush.verticalGradient(listOf(Color.White, Color.Transparent)),
                    fontFamily = sfProFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                ),
            )
            UiKitShowMoreText(
                modifier = Modifier
                    .constrainAs(showMore) {
                        top.linkTo(textStubAboutBtc.bottom, 2.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                textResId = R.string.show_more_with_plus
            )
            SetInfoItems(
                modifier = Modifier.constrainAs(infoItems) {
                    top.linkTo(showMore.bottom, 24.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
        }
    }

    @Composable
    private fun DrawHeader(modifier: Modifier, market: UiMarket) {
        ConstraintLayout(modifier = modifier) {
            val (image, view, title, balance, grow, lowBalance, highBalance, volume) = createRefs()

            val shapeView = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomEnd = 24.dp,
                bottomStart = 24.dp
            )
            Box(
                modifier = Modifier
                    .constrainAs(view) { top.linkTo(parent.top) }
                    .fillMaxWidth()
                    .height(241.dp)
                    .clip(shape = shapeView)
                    .background(DarkBlue)
            )
            Image(
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onBackPressed() }
                    .constrainAs(image) {
                        top.linkTo(parent.top, 18.dp)
                        start.linkTo(parent.start, 16.dp)
                    },
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = ""
            )
            Text(
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top, 18.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                text = "${stringResource(R.string.btc_text)}/${stringResource(R.string.usd_text)}",
                style = MaterialTheme.typography.body1,
                color = Color.White
            )

            Text(
                modifier = Modifier.constrainAs(balance) {
                    top.linkTo(title.bottom, 26.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                text = "$${market.balance}",
                style = MaterialTheme.typography.h1
            )

            Text(
                modifier = Modifier.constrainAs(grow) {
                    top.linkTo(balance.bottom, 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                text = "${market.growth}%",
                color = if (market.isGrowthUp) DarkGreen else DarkRed,
                style = MaterialTheme.typography.body1
            )

            SetBalanceItem(
                modifier = Modifier.constrainAs(lowBalance) {
                    start.linkTo(parent.start, 20.dp)
                    top.linkTo(grow.bottom, 26.dp)
                },
                textResId = R.string.low_text,
                text = "$${market.lowBalance}"
            )

            SetBalanceItem(
                modifier = Modifier.constrainAs(highBalance) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(grow.bottom, 26.dp)
                },
                textResId = R.string.high_text,
                text = "$${market.highBalance}"
            )
            SetBalanceItem(
                modifier = Modifier.constrainAs(volume) {
                    end.linkTo(parent.end, 20.dp)
                    top.linkTo(grow.bottom, 26.dp)
                },
                textResId = R.string.volume_btc,
                text = market.volume.toString()
            )
        }
    }

    @Composable
    private fun SetBalanceItem(modifier: Modifier, @StringRes textResId: Int, text: String) {
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(id = textResId),
                style = MaterialTheme.typography.subtitle2,
                color = TypographyGray
            )
            Text(
                modifier = Modifier.padding(top = 7.dp),
                text = text,
                style = MaterialTheme.typography.body1,
            )
        }
    }

    @Composable
    private fun SetTextFields(modifier: Modifier, state: CurrencyPageState) {
        val availableText = stringResource(id = R.string.available_text)

        Column(modifier = modifier) {
            UiKitTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.btcValue,
                onValueChanged = { viewModel.handleAction(SetBtcValueAction(it)) },
                currencyResId = R.string.btc_text,
                descriptionText = "$availableText ${stringResource(id = R.string.btc_text)}"
            )
            UiKitTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.usdValue,
                onValueChanged = { viewModel.handleAction(SetUsdValueAction(it)) },
                currencyResId = R.string.usd_text,
                descriptionText = "$availableText ${stringResource(id = R.string.usd_text)}"
            )
        }
    }

    @Composable
    private fun SetInfoItems(modifier: Modifier) {
        Column(modifier = modifier.padding(horizontal = 20.dp)) {
            SetInfoItem(
                modifier = Modifier.fillMaxWidth(),
                title = "Rank",
                description = "№1"
            )
            SetInfoItem(
                modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                title = "Launch Date",
                description = "January 3, 2009"
            )
        }
    }

    @Composable
    private fun SetInfoItem(modifier: Modifier, title: String, description: String) {
        Row(
            modifier = modifier
                .background(color = DarkBlue, Shapes.medium)
                .padding(14.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, style = MaterialTheme.typography.body1)
            Text(
                modifier = Modifier.weight(1F),
                text = description,
                style = MaterialTheme.typography.caption,
                color = TypographyGray,
                textAlign = TextAlign.End
            )
        }
    }
}