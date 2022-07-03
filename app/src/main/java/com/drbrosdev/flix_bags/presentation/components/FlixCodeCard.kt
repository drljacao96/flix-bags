package com.drbrosdev.flix_bags.presentation.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.drbrosdev.flix_bags.util.QRGenUtils
import compose.icons.FeatherIcons
import compose.icons.feathericons.Info

@Composable
fun FlixCodeCard(
    modifier: Modifier = Modifier,
    codeContent: String,
    useTopText: Boolean = true,
    useBottomText: Boolean = true,
    useAction: Boolean = true,
    onScanTicket: (() -> Unit)? = null
) {
    val backgroundColor = MaterialTheme.colors.secondary
    val codeColor = MaterialTheme.colors.secondaryVariant

    val isCodeEmpty = codeContent.isBlank()

    val codeImage = QRGenUtils.createCodeBitmap(
        codeContent = codeContent,
        backgroundColor = backgroundColor.toArgb(),
        codeColor = codeColor.toArgb()
    )

    val elevation by animateDpAsState(targetValue = if (isCodeEmpty) 0.dp else 4.dp)

    Surface(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .then(modifier),
        color = backgroundColor,
        elevation = elevation
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(18.dp).fillMaxHeight()
        ) {
            if (useTopText)
                Text(
                    text = "QR Kod",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onSurface
                )
            Spacer(modifier = Modifier.height(12.dp))

            if (isCodeEmpty) {
                CodeCardPlaceholder(
                    modifier = Modifier.padding(24.dp).weight(1f)
                )
            } else {
                Image(
                    bitmap = codeImage.asImageBitmap(),
                    contentDescription = "null",
                    modifier = Modifier
                        .weight(1f)
                        .size(200.dp),
                )
                if (useBottomText) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Kod: $codeContent",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            if (useAction)
                FlixScanCodeButton(
                    actionText = "Skenirajte kod",
                    supportText = "Pritisnite dugme da skenirate QR kod!",
                    modifier = Modifier.padding(bottom = 4.dp)
                ) { onScanTicket?.invoke() }
        }
    }
}

@Composable
private fun CodeCardPlaceholder(
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .then(modifier)
    ) {
        Image(
            imageVector = FeatherIcons.Info,
            contentDescription = "null",
            modifier = Modifier.size(64.dp),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onSurface)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Pritisnite Skeniraj kod da vidite vas kod!",
            color = MaterialTheme.colors.onSurface,
            textAlign = TextAlign.Center
        )
    }
}