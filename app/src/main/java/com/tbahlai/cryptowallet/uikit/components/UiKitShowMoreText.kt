package com.tbahlai.cryptowallet.uikit.components

import androidx.annotation.StringRes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.tbahlai.cryptowallet.uikit.theme.DarkPink
import com.tbahlai.cryptowallet.uikit.theme.LightPink
import com.tbahlai.cryptowallet.uikit.theme.sfProFamily

@OptIn(ExperimentalTextApi::class)
@Composable
fun UiKitShowMoreText(
    modifier: Modifier = Modifier,
    @StringRes textResId: Int,
    textAlign: TextAlign = TextAlign.Center
) {
    Text(
        modifier = modifier,
        text = stringResource(textResId),
        textAlign = textAlign,
        style = TextStyle(
            brush = Brush.linearGradient(listOf(LightPink, DarkPink)),
            fontFamily = sfProFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
    )
}