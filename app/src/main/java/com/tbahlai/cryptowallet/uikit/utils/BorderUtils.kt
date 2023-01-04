package com.tbahlai.cryptowallet.uikit.utils

import androidx.compose.foundation.border
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tbahlai.cryptowallet.uikit.theme.DarkPink
import com.tbahlai.cryptowallet.uikit.theme.LightPink
import com.tbahlai.cryptowallet.uikit.theme.Shapes

fun Modifier.buttonBorder(width: Dp = 1.dp, shape: Shape = Shapes.medium) : Modifier {
    return this.border(
        width = width,
        brush = Brush.linearGradient(colors = listOf(LightPink, DarkPink)),
        shape = shape,
    )
}