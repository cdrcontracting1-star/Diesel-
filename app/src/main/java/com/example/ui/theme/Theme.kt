package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = CyanAccent,
    onPrimary = TechNavy900,
    primaryContainer = TechNavy700,
    onPrimaryContainer = CyanAccent,
    secondary = IndigoLight,
    onSecondary = TechNavy900,
    secondaryContainer = Color(0xFF312E81),
    onSecondaryContainer = IndigoLight,
    tertiary = GoldAccent,
    onTertiary = TechNavy900,
    background = TechNavy900,
    onBackground = Color(0xFFF8FAFC),
    surface = TechNavy800,
    onSurface = Color(0xFFF8FAFC),
    surfaceVariant = TechNavy700,
    onSurfaceVariant = TechSlate400
)

private val LightColorScheme = lightColorScheme(
    primary = CyanPrimary,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE0F2FE),
    onPrimaryContainer = Color(0xFF0369A1),
    secondary = IndigoPrimary,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFEEF2FF),
    onSecondaryContainer = IndigoPrimary,
    tertiary = GoldAccent,
    onTertiary = Color.White,
    background = TechSlate50,
    onBackground = TechNavy900,
    surface = Color.White,
    onSurface = TechNavy900,
    surfaceVariant = TechSlate100,
    onSurfaceVariant = TechSlate600
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Use our tuned tech brand colors for consistency
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
