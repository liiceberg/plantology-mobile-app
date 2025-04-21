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

private val ExperimentalColorScheme = lightColorScheme(
    primary = Primary500,
    onPrimary = Secondary00,
    primaryContainer = Primary200,
    onPrimaryContainer = Neutral900,
    inversePrimary = Primary100,
    secondary = Neutral700,
    onSecondary = Secondary00,
    secondaryContainer = Neutral200,
    onSecondaryContainer = Neutral800,
    tertiary = Warning500,
    onTertiary = Secondary00,
    tertiaryContainer = Warning400,
    onTertiaryContainer = Neutral900,
    background = Neutral50,
    onBackground = Neutral900,
    surface = Secondary00,
    onSurface = Neutral900,
    surfaceVariant = Neutral100,
    onSurfaceVariant = Neutral600,
    surfaceTint = Primary500,
    inverseSurface = Neutral900,
    inverseOnSurface = Secondary00,
    error = Destructive500,
    onError = Secondary00,
    errorContainer = Destructive600,
    onErrorContainer = Secondary00,
    outline = Neutral300,
    outlineVariant = Neutral400,
    scrim = Neutral900.copy(alpha = 0.5f),
    surfaceBright = Secondary00,
    surfaceContainer = Neutral100,
    surfaceContainerHigh = Neutral200,
    surfaceContainerHighest = Neutral300,
    surfaceContainerLow = Neutral50,
    surfaceContainerLowest = Secondary00,
    surfaceDim = Neutral100
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