package com.drbrosdev.flix_bags.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun FlixBaggageStatus(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    numOfBags: Int
) {
    AnimatedVisibility(
        visible = isVisible,
        modifier = Modifier.then(modifier),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column(
            //modifier = Modifier.then(modifier),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "$numOfBags", style = MaterialTheme.typography.h2.copy(fontWeight = FontWeight.Bold))
            //Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Baggage #", style = MaterialTheme.typography.caption.copy(color = Color.Gray))
        }
    }
}