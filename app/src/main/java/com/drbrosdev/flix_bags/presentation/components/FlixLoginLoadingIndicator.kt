package com.drbrosdev.flix_bags.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FlixLoginLoadingIndicator(
    isLoading: Boolean,
    modifier: Modifier = Modifier
){
    if (isLoading) {
        LinearProgressIndicator(
            modifier = modifier,
            color = MaterialTheme.colors.onBackground
        )
    } else {
        Spacer(modifier = Modifier.height(14.dp))
    }
}