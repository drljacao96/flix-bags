package com.drbrosdev.flix_bags.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.drbrosdev.flix_bags.R

@Composable
fun FlixPasswordTextField(
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    text: String,
    onTextChanged: (String) -> Unit,
) {
    val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        cursorColor = MaterialTheme.colors.secondaryVariant,
        focusedBorderColor = MaterialTheme.colors.secondaryVariant,
        focusedLabelColor = MaterialTheme.colors.secondaryVariant
    )

    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    var foo by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .then(modifier)
    ) {
        OutlinedTextField(
            isError = foo,
            value = text,
            onValueChange = {
                foo = it.isBlank()
                onTextChanged(it)
            },
            label = { Text("Password") },
            singleLine = true,
            placeholder = { Text("Password") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible)
                    painterResource(id = R.drawable.ic_baseline_visibility_24)
                else painterResource(id = R.drawable.ic_baseline_visibility_off_24)

                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(painter = image, contentDescription = description)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = textFieldColors
        )
        AnimatedVisibility(visible = foo) {
            Text(
                text = "Field cannot be empty.",
                style = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.error),
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }
}