package ru.itis.liiceberg.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Primary600,
    primaryContainer = Secondary00,
    onPrimary = Secondary00,
    secondary = Secondary00,
    outline = Neutral200,
    onSurfaceVariant = Neutral600,
    onError = Destructive600,
    onBackground = Neutral500,
    surface = Primary50,
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}