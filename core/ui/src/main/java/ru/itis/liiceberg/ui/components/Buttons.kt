package ru.itis.liiceberg.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.itis.liiceberg.ui.R
import ru.itis.liiceberg.ui.theme.AppTheme

@Composable
fun SimpleButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(onClick = onClick, modifier.fillMaxWidth(), enabled = enabled) {
        Text(text = text)
    }
}

@Composable
fun SimpleButtonWithStartIcon(
    text: String,
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(onClick = onClick, modifier.fillMaxWidth(), enabled = enabled) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 8.dp)
                .size(24.dp),
            tint = Color.Unspecified
        )
        Text(text = text)

    }
}

@Composable
fun SimpleIconButton(
    icon: ImageVector,
    size: Dp,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
        SimpleIcon(icon = icon, size = size, modifier = modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewButton() {

    AppTheme {
        Column {
            SimpleButton(text = "Example") {}
            SimpleButtonWithStartIcon(
                text = "Example",
                icon = R.drawable.app_icon_primary,
            ) {}
            SimpleIconButton(icon = Icons.AutoMirrored.Filled.ArrowBack, size = 54.dp) {
                
            }
        }
    }
}

