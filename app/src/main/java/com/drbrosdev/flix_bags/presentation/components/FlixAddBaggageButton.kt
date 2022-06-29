package com.drbrosdev.flix_bags.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowRight

@Composable
fun FlixAddBaggageButton(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    actionText: String,
    supportText: String,
    onClick: () -> Unit
) {
    val cornerShape = RoundedCornerShape(20.dp)

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = Modifier.then(modifier)
    ) {
        Box(
            modifier = Modifier
                .clip(cornerShape)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.secondaryVariant,
                    shape = cornerShape
                )
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = actionText,
                        style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onSurface),
                    )
                    Text(
                        text = supportText,
                        style = MaterialTheme.typography.caption.copy(color = Color.Gray)
                    )
                }
                Image(
                    imageVector = FeatherIcons.ArrowRight,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onSurface)
                )
            }
        }
    }
}