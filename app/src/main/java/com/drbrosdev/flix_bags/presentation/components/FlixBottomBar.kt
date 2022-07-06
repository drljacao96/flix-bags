package com.drbrosdev.flix_bags.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FlixBottomBar(
    modifier: Modifier = Modifier,
    onFinish: () -> Unit,
    onNext: () -> Unit
) {
    Surface(
        modifier = Modifier
            .then(modifier),
        color = MaterialTheme.colors.background
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FlixSubmitButton(
                text = "Ne",
                useFinishAction = true,
                modifier = Modifier
                    .weight(1f)
            ) {
                onFinish()
            }

            Spacer(modifier = Modifier.width(12.dp))

            FlixSubmitButton(
                text = "Da",
                modifier = Modifier
                    .weight(1f)
            ) {
                onNext()
            }
        }
    }
}