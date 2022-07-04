package com.drbrosdev.flix_bags.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.drbrosdev.flix_bags.presentation.home.CodeComparisonState
import java.util.*

@Composable
fun FlixCodeCard(
    modifier: Modifier = Modifier,
    firstCodeContent: String,
    secondCodeContent: String,
    comparisonState: CodeComparisonState = CodeComparisonState.IDLE,
    useBottomText: Boolean = true,
) {
    val surfaceBackground by animateColorAsState(targetValue = when(comparisonState) {
        CodeComparisonState.IDLE -> MaterialTheme.colors.secondary
        CodeComparisonState.TRUE -> MaterialTheme.colors.onError
        CodeComparisonState.FALSE -> MaterialTheme.colors.error
    } )

    Surface(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .height(IntrinsicSize.Max)
            .then(modifier),
        color = surfaceBackground,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 18.dp, vertical = 6.dp)
        ) {
            Text(
                text = "QR Kodovi".uppercase(Locale.ROOT),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onSurface)
            )
            Spacer(modifier = Modifier.height(12.dp))

            FlixQrCodeImage(
                codeContent = firstCodeContent,
                useBottomText = useBottomText,
                backgroundColor = surfaceBackground
            )

            FlixQrCodeImage(
                codeContent = secondCodeContent,
                useBottomText = useBottomText,
                backgroundColor = surfaceBackground
            )
        }
    }
}