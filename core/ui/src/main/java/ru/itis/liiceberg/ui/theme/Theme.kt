package ru.itis.liiceberg.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Primary600,
    onPrimary = Secondary00,
    primaryContainer = Neutral50,
    onPrimaryContainer = Primary700,
    secondary = Secondary00,
    onSecondary = Secondary100,
    secondaryContainer = Neutral200,
    outline = Primary600,
    error = Destructive500,
    onError = Destructive600,
    onBackground = Neutral500,
    surface = Primary50,
    onSurface = Neutral600,
    onSurfaceVariant = Neutral600,
    surfaceContainerHigh = Secondary00,
    surfaceContainerHighest = Neutral50,
)

@Composable
fun PlantologyTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}