package com.drbrosdev.flix_bags.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
    text: String = "Tekst za instrukcije informacije i kontakt."
) {
    Column(
        modifier = Modifier
            .then(modifier)
    ) {
        Text(
            text = headerText,
            style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onSurface),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text, style = MaterialTheme.typography.caption.copy(color = Color.Gray))
    }
}