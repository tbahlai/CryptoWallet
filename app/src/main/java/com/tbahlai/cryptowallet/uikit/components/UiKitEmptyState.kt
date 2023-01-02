package com.tbahlai.cryptowallet.uikit.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun UiKitEmptyState(
    modifier: Modifier = Modifier,
    isEmpty: Boolean,
    imageResId: Int,
    message: String
) {
    if (isEmpty) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(256.dp),
                painter = painterResource(id = imageResId),
                contentDescription = ""
            )
            Text(modifier = Modifier.padding(16.dp), text = message)
        }
    }
}