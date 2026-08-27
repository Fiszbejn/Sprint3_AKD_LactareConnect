package com.davifiszbejn557716.lactareconnect.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LcColorScheme = lightColorScheme(
    primary = Brand,
    onPrimary = White,
    primaryContainer = BrandTint,
    onPrimaryContainer = Brand,
    secondary = BrandLight,
    onSecondary = White,
    tertiary = AccentPink,
    onTertiary = White,
    background = Bg,
    onBackground = Ink,
    surface = White,
    onSurface = Ink,
    surfaceVariant = LineSoft,
    onSurfaceVariant = Muted,
    outline = Line,
    outlineVariant = LineSoft,
    error = AccentRed,
    onError = White
)

// Marca fixa (brand restrita) — sem dark theme nem dynamic color, conforme design system.
@Composable
fun LactareConnectTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LcColorScheme,
        typography = Typography,
        content = content
    )
}
