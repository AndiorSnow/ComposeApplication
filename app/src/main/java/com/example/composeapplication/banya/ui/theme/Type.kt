package com.example.composeapplication.banya.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle

//private val EczarFontFamily = FontFamily(
//    Font(R.font.eczar_regular),
//    Font(R.font.eczar_semibold, FontWeight.SemiBold)
//)
//private val RobotoCondensed = FontFamily(
//    Font(R.font.robotocondensed_regular),
//    Font(R.font.robotocondensed_light, FontWeight.Light),
//    Font(R.font.robotocondensed_bold, FontWeight.Bold)
//)

val Typography = Typography(
    //defaultFontFamily = RobotoCondensed,
    displaySmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp
    ),
    headlineSmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    ),
    labelSmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    ),
    titleSmall = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        letterSpacing = (.5).sp,
        fontSize = 18.sp
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
)
