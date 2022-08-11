package com.drbrosdev.flix_bags.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.drbrosdev.flix_bags.R
import com.drbrosdev.flix_bags.presentation.theme.FlixAmberLight
import com.drbrosdev.flix_bags.presentation.theme.FlixButton
import java.util.*

@Composable
fun FlixTopBar(
    modifier: Modifier = Modifier,
    text: String
) {

    Box(
        modifier = Modifier
            .background(color = FlixAmberLight)
            .then(modifier),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_bags_foreground),
                contentDescription = "",
                modifier = Modifier.size(72.dp),
            )
            Text(
                text = text.uppercase(Locale.ROOT),
                style = MaterialTheme.typography.body1.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold
                ),
            )
        }
    }
}



