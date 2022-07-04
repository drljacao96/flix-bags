package com.drbrosdev.flix_bags.presentation.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.drbrosdev.flix_bags.presentation.theme.FlixAmberDark
import com.drbrosdev.flix_bags.presentation.theme.FlixButton
import compose.icons.FeatherIcons
import compose.icons.feathericons.Camera
import java.util.*

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
        targetValue = FlixAmberDark,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .clip(cornerShape)
            .border(
                width = 4.dp,
                color = borderColor,
                shape = cornerShape
            )
            .background(color = FlixButton)
            .clickable { onClick() }
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = actionText.uppercase(Locale.ROOT),
                    style = MaterialTheme.typography.body1.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    ),
                )
                Text(
                    text = supportText,
                    style = MaterialTheme.typography.caption.copy(color = Color.White),
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
            Image(
                imageVector = FeatherIcons.Camera,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                colorFilter = ColorFilter.tint(color = Color.White)
            )
        }
    }
}