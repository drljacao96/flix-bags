package com.drbrosdev.flix_bags.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.drbrosdev.flix_bags.R

val font = FontFamily(
    Font(R.font.raleway_regular, weight = FontWeight.Normal),
    Font(R.font.raleway_bold, weight = FontWeight.Bold),
    Font(R.font.raleway_light, weight = FontWeight.Light),
    Font(R.font.raleway_semibold, weight = FontWeight.SemiBold),
)

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = font,
    h1 = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp
    ),
    h2 = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.SemiBold,
        fontSize = 34.sp,
        letterSpacing = (-2).sp
    ),
    h3 = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp
    ),
    h4 = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    body1 = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Light,
        fontSize = 10.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        fontFeatureSettings = "c2sc, smcp" //some voodoo magic shit
    )
)