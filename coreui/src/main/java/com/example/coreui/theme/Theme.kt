package com.example.coreui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.hr_app.presentation.theme.AppColors
import com.example.hr_app.presentation.theme.Typography

private val LightColorScheme = darkColorScheme()

private val DarkColorScheme = AppColors.run {
    lightColorScheme(
        primary = Green,
        secondary = DarkGreen,
        tertiary = Blue,

        background = Black,
        onBackground = White,

        surfaceVariant = Grey1,
        surface = Grey2,
        onSurface = White,
        onSurfaceVariant = White,

        outlineVariant = Grey1
    )
}

@Composable
fun MyHRAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme
//    when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }

    CompositionLocalProvider(
        LocalSpacing provides Spacing()
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}