package com.drbrosdev.flix_bags.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.drbrosdev.flix_bags.util.QRGenUtils
import compose.icons.FeatherIcons
import compose.icons.feathericons.Info

@Composable
fun FlixQrCodeImage(
    codeContent: String,
    backgroundColor: Color = MaterialTheme.colors.secondary
) {
    val codeColor = MaterialTheme.colors.secondaryVariant

    val codeImage = QRGenUtils.createCodeBitmap(
        codeContent = codeContent,
        backgroundColor = backgroundColor.toArgb(),
        codeColor = codeColor.toArgb()
    )

    if (codeContent.isNotBlank()) {
        Text(
            text = "Kod: $codeContent",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onSurface
        )
    }
    Image(
        bitmap = codeImage.asImageBitmap(),
        contentDescription = "null",
        modifier = Modifier
            .size(144.dp),
    )

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
    }
}