package com.drbrosdev.flix_bags.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun FlixAddDialogFragmentInfoText(
    modifier: Modifier = Modifier,
    headerText: String = "",
    text: String = ""
) {
    Column(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .then(modifier)
    ) {
        Text(
            text = headerText,
            style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.onSurface),
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.h6.copy(color = Color.Gray),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}