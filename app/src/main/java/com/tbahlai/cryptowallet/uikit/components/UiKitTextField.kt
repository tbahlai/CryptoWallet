package com.tbahlai.cryptowallet.uikit.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tbahlai.cryptowallet.uikit.theme.DarkPink
import com.tbahlai.cryptowallet.uikit.theme.LightPink
import com.tbahlai.cryptowallet.uikit.theme.TypographyGray

@Composable
fun UiKitTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit,
    @StringRes currencyResId: Int,
    descriptionText: String,
    keyboardType: KeyboardType = KeyboardType.Number
) {
    ConstraintLayout(modifier = modifier) {
        val (textField, currencyText, description) = createRefs()

        TextField(
            modifier = Modifier
                .constrainAs(textField) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth(),
            value = value,
            onValueChange = onValueChanged,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = DarkPink,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = DarkPink,
                unfocusedIndicatorColor = LightPink
            ),
            textStyle = MaterialTheme.typography.body1,
        )

        Text(
            modifier = Modifier
                .constrainAs(currencyText) {
                    end.linkTo(parent.end)
                    bottom.linkTo(textField.bottom, 18.dp)
                },
            text = stringResource(id = currencyResId),
            style = MaterialTheme.typography.body1
        )

        Text(
            modifier = Modifier
                .constrainAs(description) {
                    start.linkTo(parent.start)
                    top.linkTo(textField.bottom, 6.dp)
                },
            text = descriptionText,
            color = TypographyGray,
            style = MaterialTheme.typography.subtitle2
        )
    }
}