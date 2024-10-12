package com.example.coreui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.hr_app.presentation.theme.AppColors
import com.example.hr_app.presentation.theme.Typography

private val DarkColorScheme = darkColorScheme()

private val LightColorScheme = AppColors.run {
    lightColorScheme(
        primary = Green,
        secondary = DarkGreen,
        tertiary = Blue,

        background = Black,
        surfaceVariant = Grey1,
        surface = Grey2,
        onSurface = White,
        onSurfaceVariant = White,

        outlineVariant = Grey1,

        /* Other default colors to override
        onPrimary = Color.White,
        onSecondary = Color.White,
        onTertiary = Color.White,
        onBackground = Color(0xFF1C1B1F),
        onSurface = Color(0xFF1C1B1F),
        */
    )
}

@Composable
fun MyHRAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme
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

    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}