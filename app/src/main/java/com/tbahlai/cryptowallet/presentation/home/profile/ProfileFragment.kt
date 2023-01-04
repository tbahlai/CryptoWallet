package com.tbahlai.cryptowallet.presentation.home.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.tbahlai.cryptowallet.R
import com.tbahlai.cryptowallet.common.BaseComposeFragment
import com.tbahlai.cryptowallet.presentation.home.models.UiMarket
import com.tbahlai.cryptowallet.presentation.home.models.UiNew
import com.tbahlai.cryptowallet.presentation.home.models.UiUser
import com.tbahlai.cryptowallet.uikit.components.UiKitButton
import com.tbahlai.cryptowallet.uikit.components.UiKitScaffold
import com.tbahlai.cryptowallet.uikit.components.UiKitShowMoreText
import com.tbahlai.cryptowallet.uikit.components.UiKitTitle
import com.tbahlai.cryptowallet.uikit.theme.*
import com.tbahlai.cryptowallet.uikit.utils.toTimeAgo
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class ProfileFragment : BaseComposeFragment() {

    override val viewModel by viewModels<ProfileViewModel>()

    @Composable
    override fun ScaffoldData(content: @Composable () -> Unit) {
        UiKitScaffold { content() }
    }

    @Composable
    override fun ScreenContent() {
        val state by viewModel.state.collectAsState()
        val currentUser = state.currentUserData ?: return

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SetupUserData(currentUser = currentUser)

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 32.dp)
            ) {
                UiKitButton(
                    modifier = Modifier.weight(1F).padding(end = 5.dp),
                    text = stringResource(R.string.deposit_text)
                ) { }

                UiKitButton(
                    modifier = Modifier.weight(1F).padding(start = 5.dp),
                    text = stringResource(R.string.withdraw_text)
                ) {}
            }

            SetupMarkets(marketsList = state.marketsList)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 42.dp)
            ) {
                UiKitTitle(textResId = R.string.news_text, drawableResId = R.drawable.ic_news)
                UiKitShowMoreText(
                    modifier = Modifier.weight(1F),
                    textResId = R.string.show_all,
                    textAlign = TextAlign.End
                )
            }
            SetupNews(newsList = state.newsList)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun SetupUserData(currentUser: UiUser) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, end = 16.dp, top = 24.dp)) {
        Column(modifier = Modifier.weight(1F)) {
            Text(
                text = stringResource(R.string.welcome_back),
                style = typography.body1,
                color = TypographyGray
            )
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = currentUser.name, style = typography.h3)
                Image(
                    modifier = Modifier.size(32.dp).padding(start = 10.dp),
                    painter = painterResource(id = R.drawable.ic_hands),
                    contentDescription = null
                )
            }
        }
        GlideImage(
            modifier = Modifier.size(60.dp).clip(CircleShape),
            model = currentUser.imageUrl,
            contentDescription = null,
        )
    }
    SetupUserBalance(balance = currentUser.currentBalance)
}

@Composable
private fun SetupUserBalance(balance: Double) {
    Box(modifier = Modifier.padding(top = 36.dp), contentAlignment = Alignment.Center) {
        Image(
            modifier = Modifier.size(170.dp),
            painter = painterResource(id = R.drawable.ic_blur),
            contentDescription = null,
            alpha = 0.8F
        )
        Box(
            modifier = Modifier
                .size(180.dp)
                .alpha(0.5f)
                .clip(CircleShape)
                .background(DarkBlue)
        )
        Text(text = "$${balance}", style = typography.h4)
    }
}

@Composable
private fun SetupMarkets(marketsList: List<UiMarket>) {
    UiKitTitle(
        modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, top = 42.dp),
        textResId = R.string.trending,
        drawableResId = R.drawable.ic_fire
    )

    Row(modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()).padding(16.dp)) {
        marketsList.forEachIndexed { index, market ->
            val paddingStart = if (index == 0) 0.dp else 8.dp
            val paddingEnd = if (index == marketsList.size - 1) 0.dp else 8.dp
            Column(
                modifier = Modifier
                    .padding(start = paddingStart, end = paddingEnd)
                    .background(
                        shape = Shapes.large,
                        alpha = 0.12f,
                        brush = Brush.linearGradient(listOf(Color.White, Color.White))
                    )
                    .padding(16.dp)
            ) {
                Row {
                    Image(painter = painterResource(id = market.logo), contentDescription = null)
                    Column(modifier = Modifier.padding(start = 10.dp)) {
                        Text(text = market.name, style = typography.body1, color = Color.White)
                        Text(
                            modifier = Modifier.padding(top = 2.dp),
                            text = market.description,
                            style = typography.subtitle1,
                            color = TypographyGray
                        )
                    }
                }
                Image(
                    modifier = Modifier.padding(top = 14.dp),
                    painter = painterResource(id = R.drawable.ic_stub_chart),
                    contentDescription = null,
                )
                SetupMarketBalance(market = market)
            }
        }
    }
}

@Composable
private fun SetupMarketBalance(market: UiMarket) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.width(124.dp),
            text = "$${market.balance}",
            color = Color.White,
            style = typography.body2
        )
        val painterRes = when (market.isGrowthUp) {
            false -> R.drawable.ic_arrow_down
            true -> R.drawable.ic_arrow_up
        }
        Icon(
            modifier = Modifier,
            painter = painterResource(id = painterRes),
            contentDescription = null,
            tint = if (market.isGrowthUp) BasicGreen else BasicRed
        )
        Text(
            text = "${abs(market.growth)}%",
            color = if (market.isGrowthUp) BasicGreen else BasicRed,
            style = typography.subtitle1
        )
    }
}

@Composable
private fun SetupNews(newsList: List<UiNew>) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 28.dp),
        verticalArrangement = Arrangement.Center
    ) {
        val listOfImages = listOf(R.drawable.ic_new_xrp, R.drawable.ic_new_market_watch)
        newsList.forEach { new ->
            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                Image(
                    modifier = Modifier.size(80.dp),
                    painter = painterResource(id = listOfImages.random()),
                    contentDescription = null
                )
                SetupNewsItemData(new)
            }
        }
    }
}

@Composable
private fun SetupNewsItemData(new: UiNew) {
    Column(modifier = Modifier.padding(start = 24.dp), verticalArrangement = Arrangement.Center) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = new.sourceName, color = TypographyGray, style = typography.subtitle2)
            Box(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(5.dp)
                    .clip(CircleShape)
                    .background(TypographyGray)
            )
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = new.publishedDate.toTimeAgo(),
                color = TypographyGray,
                style = typography.subtitle2
            )
        }

        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = new.title,
            color = Color.White,
            style = typography.body2
        )
    }
}