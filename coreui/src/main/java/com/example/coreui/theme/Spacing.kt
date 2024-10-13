package com.example.coreui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing (
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 12.dp,
    val large: Dp = 26.dp,
    val extraLarge: Dp = 52.dp
)

val LocalSpacing = compositionLocalOf { Spacing() }

val MaterialTheme.spacings: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current