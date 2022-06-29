package com.drbrosdev.flix_bags.presentation.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.drbrosdev.flix_bags.presentation.theme.FlixGreenAttentive
import com.drbrosdev.flix_bags.presentation.theme.FlixGreenDark
import compose.icons.FeatherIcons
import compose.icons.feathericons.Camera

@Composable
fun FlixScanCodeButton(
    modifier: Modifier = Modifier,
    actionText: String,
    supportText: String,
    onClick: () -> Unit
) {
    val cornerShape = RoundedCornerShape(20.dp)

    val infiniteTransition = rememberInfiniteTransition()
    val borderColor by infiniteTransition.animateColor(
        initialValue = Color.Transparent,
        targetValue = FlixGreenDark,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .clip(cornerShape)
            .border(
                width = 3.dp,
                color = borderColor,
                shape = cornerShape
            )
            .background(color = FlixGreenAttentive)
            .clickable { onClick() }
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = actionText,
                    style = MaterialTheme.typography.body1.copy(color = Color.Black),
                )
                Text(
                    text = supportText,
                    style = MaterialTheme.typography.caption.copy(color = Color.Gray)
                )
            }
            Image(
                imageVector = FeatherIcons.Camera,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                colorFilter = ColorFilter.tint(color = Color.Black)
            )
        }
    }
}