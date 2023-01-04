package com.tbahlai.cryptowallet.uikit.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun UiKitTitle(
    modifier: Modifier = Modifier,
    @StringRes textResId: Int,
    @DrawableRes drawableResId: Int
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = drawableResId),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = stringResource(textResId),
            style = MaterialTheme.typography.h4,
            color = Color.White
        )
    }
}