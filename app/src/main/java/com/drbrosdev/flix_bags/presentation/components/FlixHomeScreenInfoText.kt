package com.drbrosdev.flix_bags.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FlixHomeScreenInfoText(
    modifier: Modifier = Modifier,
    headerText: String = "Instructions or Ticket info",
    text: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
) {
    Column(
        modifier = Modifier
            .then(modifier)
    ) {
        Text(
            text = headerText,
            style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onSurface)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text, style = MaterialTheme.typography.caption.copy(color = Color.Gray))
    }
}