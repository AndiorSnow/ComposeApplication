package com.example.composeapplication.banya.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

val DarkBlue900 = Color(0xFF26282F)
val Pink = Color(0xFFfa5a80)
val gray = Color(0xFFf2f2f2)
val darkGray = Color.DarkGray

// Rally is always dark themed.
val ColorPalette = darkColorScheme(
    primary = Color.White,
    onPrimary = Color.Black,
    surface = Pink,
    onSurface = Pink,
    background = gray,
    onBackground = gray,
    secondary = darkGray,

)
