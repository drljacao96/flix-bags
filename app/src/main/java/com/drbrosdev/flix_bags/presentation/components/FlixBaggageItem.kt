package com.drbrosdev.flix_bags.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.drbrosdev.flix_bags.R


@Composable
fun FlixBaggageList(
    modifier: Modifier = Modifier,
    items: List<String>,
    onItemClick: (String) -> Unit,
    lazyListState: LazyListState = rememberLazyListState()
) {
    LazyColumn(
        state = lazyListState,
        modifier = Modifier
            .then(modifier),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        item {
            Surface(
                modifier = Modifier,
                color = MaterialTheme.colors.background
            ) {
                Text(
                    text = "Your Baggage",
                    style = MaterialTheme.typography.h4
                )
            }
        }

        item {
            if (items.isEmpty()) {
                Box(
                    modifier = Modifier.fillParentMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Baggage list is empty.",
                        style = MaterialTheme.typography.h2
                            .copy(color = Color.Gray, fontWeight = FontWeight.Thin)
                    )
                }
            }
        }

        items(items) {
            FlixBaggageItem(
                baggageText = it,
                onClick = { onItemClick(it) }
            )
        }
    }
}

@Composable
fun FlixBaggageItem(
    modifier: Modifier = Modifier,
    baggageText: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_backpack_24),
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(text = baggageText, style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Normal))
    }
}