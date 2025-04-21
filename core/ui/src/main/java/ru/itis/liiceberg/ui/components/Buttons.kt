package ru.itis.liiceberg.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.itis.liiceberg.ui.R
import ru.itis.liiceberg.ui.theme.PlantologyTheme

@Composable
fun SimpleButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(onClick = onClick, modifier.fillMaxWidth(), enabled = enabled) {
        TitleSmallText(text = text)
    }
}

@Composable
fun SimpleOutlinedButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    OutlinedButton(onClick = onClick, modifier.fillMaxWidth(), enabled = enabled) {
        TitleSmallText(text = text)
    }
}

@Composable
fun SimpleOutlinedButtonWithStartIcon(
    text: String,
    icon: Painter,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.outline,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    OutlinedButton(onClick = onClick, modifier.fillMaxWidth(), enabled = enabled) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier
                .padding(end = 8.dp)
                .size(18.dp),
            tint = tint
        )
        TitleSmallText(text = text)
    }
}

@Composable
fun SimpleButtonWithStartIcon(
    text: String,
    painter: Painter,
    iconSize: Dp,
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(onClick = onClick, modifier.fillMaxWidth(), enabled = enabled) {
        SimpleIcon(
            painter = painter,
            size = iconSize,
            modifier = Modifier
                .padding(end = 8.dp),
            tint = tint
        )
        TitleSmallText(text = text)
    }
}

@Composable
fun SimpleIconButton(
    icon: Painter,
    size: Dp,
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified,
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
        SimpleIcon(painter = icon, size = size, modifier = modifier, tint = tint)
    }
}

@Composable
fun SimpleIconButton(
    icon: ImageVector,
    size: Dp,
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified,
    onClick: () -> Unit,
) {
    SimpleIconButton(
        icon = rememberVectorPainter(image = icon),
        size = size,
        modifier = modifier,
        tint = tint,
        onClick = onClick
    )
}

@Composable
fun SimpleFloatingActionButton(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(onClick = onClick, shape = CircleShape, modifier = modifier) {
        SimpleIcon(icon = icon, size = 40.dp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewButton() {
    PlantologyTheme {
        Column {
            SimpleButton(text = "Example") {}
            SimpleOutlinedButton(text = "Example") {}
            SimpleButtonWithStartIcon(
                text = "Example",
                painter = painterResource(id = R.drawable.app_icon_primary),
                iconSize = 24.dp
            ) {}
            SimpleOutlinedButtonWithStartIcon(
                text = "Example",
                icon = painterResource(id = R.drawable.app_icon_primary),
            ) {}
            SimpleIconButton(icon = Icons.AutoMirrored.Filled.ArrowBack, size = 54.dp) {
            }
            SimpleFloatingActionButton(icon = Icons.AutoMirrored.Filled.ArrowBack) {

            }
        }
    }
}

