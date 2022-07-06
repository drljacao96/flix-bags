package com.drbrosdev.flix_bags.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun FlixHomeScreenInfoText(
    modifier: Modifier = Modifier,
    headerText: String = "Instrukcije i Informacije",
    text: String = ""
) {
    Column(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .then(modifier)
    ) {
        Text(
            text = headerText,
            style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onSurface),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.h5.copy(color = Color.Gray),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}