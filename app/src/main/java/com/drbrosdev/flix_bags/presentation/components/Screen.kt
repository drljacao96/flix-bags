package com.drbrosdev.flix_bags.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.drbrosdev.flix_bags.presentation.theme.FlixBagsTheme

@Composable
fun Screen(
    content: @Composable () -> Unit
) {
    FlixBagsTheme() {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background,
            content = content
        )
    }
}