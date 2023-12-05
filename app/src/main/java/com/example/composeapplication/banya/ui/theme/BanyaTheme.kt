package com.example.composeapplication.banya.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/**
 * A [MaterialTheme] for Banya.
 */
@Composable
fun BanyaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
    ) { 
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        
        darkTheme -> ColorPalette
        else -> ColorPalette
    }
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        val systemUiController = rememberSystemUiController()
//        val useDarkIcons = !isSystemInDarkTheme()
//        val window = (view.context as Activity).window
//        WindowCompat.setDecorFitsSystemWindows(window, false)
//        DisposableEffect(systemUiController, useDarkIcons) {
//            // Update all of the system bar colors to be transparent, and use
//            // dark icons if we're in light theme
//            systemUiController.setSystemBarsColor(
//                color = Color.Transparent,
//                darkIcons = useDarkIcons
//            )
//            onDispose {}
//        }
//    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
