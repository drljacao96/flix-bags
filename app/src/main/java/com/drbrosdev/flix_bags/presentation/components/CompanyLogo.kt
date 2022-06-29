package com.drbrosdev.flix_bags.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.drbrosdev.flix_bags.R

@Composable
fun CompanyLogo(
    modifier: Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.ic_comp_logo),
        modifier = modifier,
        contentDescription = "Company Logo"
    )
}