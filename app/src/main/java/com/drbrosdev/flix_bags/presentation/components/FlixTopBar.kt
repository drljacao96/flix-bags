package com.drbrosdev.flix_bags.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowLeft
import compose.icons.feathericons.Info

@Composable
fun FlixTopBar(
    modifier: Modifier = Modifier,
    text: String,
    onBack: (() -> Unit)? = null,
    onInfo: () -> Unit = {}
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

            IconButton(
                onClick = { onInfo() }, modifier = Modifier
                    .size(48.dp)
                    .padding(top = 6.dp)
            ) {
                Icon(
                    imageVector = FeatherIcons.Info,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }

    }
}



