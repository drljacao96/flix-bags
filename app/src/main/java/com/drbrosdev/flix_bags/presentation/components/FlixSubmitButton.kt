package com.drbrosdev.flix_bags.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun FlixSubmitButton(
    modifier: Modifier = Modifier,
    text: String,
    useFinishAction: Boolean = false,
    onClick: () -> Unit
) {
    val cornerShape = RoundedCornerShape(20.dp)

    val buttonColor = if (useFinishAction) Color.Black
    else MaterialTheme.colors.secondaryVariant

    Box(
        modifier = Modifier
            .clip(cornerShape)
            .background(color = buttonColor)
            .clickable { onClick() }
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text.uppercase(Locale.ROOT),
            style = MaterialTheme.typography.body1.copy(color = Color.White, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(vertical = 16.dp),
        )
    }
}