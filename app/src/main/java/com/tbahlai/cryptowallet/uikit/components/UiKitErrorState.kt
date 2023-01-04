package com.tbahlai.cryptowallet.uikit.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.tbahlai.cryptowallet.R
import com.tbahlai.cryptowallet.uikit.theme.Purple500

@Composable
fun UiKitErrorState(
    context: Context,
    modifier: Modifier = Modifier,
    hasError: Boolean,
    imageResId: Int = R.drawable.ic_something_broken,
    titleText: String = context.getString(R.string.something_broken_title),
    messageText: String = context.getString(R.string.something_broken_message),
    retryAction: () -> Unit
) {
    if (hasError) {
        ConstraintLayout(modifier = modifier.fillMaxSize()) {
            val (image, title, message, button) = createRefs()

            createVerticalChain(image, title, message, chainStyle = ChainStyle.Packed)
            Image(
                modifier = Modifier
                    .size(256.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        bottom.linkTo(title.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                painter = painterResource(id = imageResId),
                contentDescription = ""
            )
            Text(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    .constrainAs(title) {
                        top.linkTo(image.bottom)
                        bottom.linkTo(message.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                text = titleText,
                textAlign = TextAlign.Center,
            )

            Text(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                    .constrainAs(message) {
                        top.linkTo(title.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                text = messageText,
                textAlign = TextAlign.Center,
            )

            Button(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                    .fillMaxWidth()
                    .constrainAs(button) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                colors = ButtonDefaults.buttonColors(backgroundColor = Purple500),
                shape = RoundedCornerShape(8.dp),
                onClick = { retryAction() }) {

                Icon(painter = painterResource(id = R.drawable.ic_retry), contentDescription = null)
                Text(modifier = Modifier.padding(start = 8.dp), text = stringResource(R.string.retry))
            }
        }
    }
}