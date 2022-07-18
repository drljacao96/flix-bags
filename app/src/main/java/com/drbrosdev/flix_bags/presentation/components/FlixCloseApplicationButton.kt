package com.drbrosdev.flix_bags.presentation.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.drbrosdev.flix_bags.presentation.theme.FlixAmberDark
import com.drbrosdev.flix_bags.presentation.theme.FlixButton
import java.util.*

@Composable
fun FlixCloseApplicationButton(
    modifier: Modifier = Modifier,
    actionText: String,
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
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = actionText.uppercase(Locale.ROOT),
                    style = MaterialTheme.typography.body1.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    ),
                )
            }
        }
    }
}