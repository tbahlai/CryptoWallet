package com.tbahlai.cryptowallet.uikit.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun UiKitProgressIndicator(isLoading: Boolean) {
    if (isLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primaryVariant),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) { ProgressIndicator() }
    }
}

@Composable
fun ProgressIndicator(
    dotSize: Int = 24,
    defaultDelay: Int = 300,
    color: Color = MaterialTheme.colors.secondary
) {
    val scaleForFirstDot by animateScaleWithDelay(0, defaultDelay)
    val scaleForSecondDot by animateScaleWithDelay(defaultDelay, defaultDelay)
    val scaleForThirdDot by animateScaleWithDelay(defaultDelay * 2, defaultDelay)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        val spaceSize = 2.dp

        Dot(scaleForFirstDot, dotSize, color = color)
        Spacer(Modifier.width(spaceSize))
        Dot(scaleForSecondDot, dotSize, color = color)
        Spacer(Modifier.width(spaceSize))
        Dot(scaleForThirdDot, dotSize, color = color)
    }
}

@Composable
fun Dot(scale: Float, dotSize: Int, color: Color) {
    Spacer(
        Modifier
            .size(dotSize.dp)
            .scale(scale)
            .background(color = color, shape = CircleShape)
    )
}


@Composable
fun animateScaleWithDelay(delay: Int, defaultDelay: Int): State<Float> {
    val infiniteTransition = rememberInfiniteTransition()

    return infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = defaultDelay * 4
                0f at delay with LinearEasing
                1f at delay + defaultDelay with LinearEasing
                0f at delay + defaultDelay * 2
            }
        )
    )
}