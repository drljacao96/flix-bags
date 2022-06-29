package com.drbrosdev.flix_bags.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FlixUserNameTextField(
    modifier: Modifier = Modifier,
    hint: String,
    isError: Boolean = false,
    text: String,
    onTextChanged: (String) -> Unit,
) {

    val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        cursorColor = MaterialTheme.colors.secondaryVariant,
        focusedBorderColor = MaterialTheme.colors.secondaryVariant,
        focusedLabelColor = MaterialTheme.colors.secondaryVariant
    )

    Column(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .then(modifier)
    ) {
        OutlinedTextField(
            value = text,
            label = {
                Text(
                    text = hint
                )
            },
            maxLines = 1,
            onValueChange = { onTextChanged(it) },
            modifier = Modifier.fillMaxWidth(),
            colors = textFieldColors,
            isError = isError
        )
        AnimatedVisibility(visible = isError) {
            Text(
                text = "Field cannot be empty.",
                style = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.error)
            )
        }
    }
}