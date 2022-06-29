package com.drbrosdev.flix_bags.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun FlixTicketDetails(
    modifier: Modifier = Modifier,
    ticket: String = "Sample ticket",
    ticketDetails: String = "These are some ticket details",
    baggageCount: Int
) {
    Column(
        modifier = Modifier
            .then(modifier),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = ticket,
            style = MaterialTheme.typography.h3.copy(
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.Normal
            )
        )
        Text(
            text = "# $baggageCount baggage items",
            style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onSurface)
        )
        Text(
            text = ticketDetails,
            style = MaterialTheme.typography.body2.copy(color = Color.Gray)
        )
    }
}