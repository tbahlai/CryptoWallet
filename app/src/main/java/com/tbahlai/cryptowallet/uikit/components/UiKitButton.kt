package com.tbahlai.cryptowallet.uikit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tbahlai.cryptowallet.uikit.theme.DarkBlue
import com.tbahlai.cryptowallet.uikit.theme.Shapes
import com.tbahlai.cryptowallet.uikit.utils.buttonBorder

@Composable
fun UiKitButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Text(
        modifier = modifier
            .background(DarkBlue, shape = Shapes.medium)
            .buttonBorder()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        text = text,
        color = Color.White,
        style = MaterialTheme.typography.body1,
        textAlign = TextAlign.Center
    )
}