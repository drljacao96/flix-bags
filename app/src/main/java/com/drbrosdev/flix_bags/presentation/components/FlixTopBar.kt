package com.drbrosdev.flix_bags.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowLeft

@Composable
fun FlixTopBar(
    modifier: Modifier = Modifier,
    text: String,
    onBack: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            onBack?.let {
                IconButton(
                    onClick = { it() },
                    modifier = Modifier
                        .size(48.dp)
                        .padding(top = 6.dp)
                ) {
                    Icon(
                        imageVector = FeatherIcons.ArrowLeft,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = MaterialTheme.colors.onSurface
                    )
                }
            }

            Text(
                text = text,
                style = MaterialTheme.typography.h2,
                fontWeight = FontWeight.SemiBold
            )
        }

    }
}



