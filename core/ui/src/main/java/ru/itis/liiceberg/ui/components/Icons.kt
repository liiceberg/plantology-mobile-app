package ru.itis.liiceberg.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.itis.liiceberg.ui.R
import ru.itis.liiceberg.ui.theme.AppTheme

@Composable
fun SimpleIcon(
    painter: Painter,
    size: Dp,
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    Icon(
        painter = painter,
        contentDescription = null,
        modifier = modifier.size(size),
        tint = tint,
    )
}

@Composable
fun LightIcon(
    painter: Painter,
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = painter,
        contentDescription = null,
        modifier = modifier
            .size(50.dp)
            .clip(
                CircleShape
            )
            .background(MaterialTheme.colorScheme.surface)
            .padding(8.dp),
        tint = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun DarkIcon(
    painter: Painter,
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = painter,
        contentDescription = null,
        modifier = modifier
            .clip(
                CircleShape
            )
            .background(MaterialTheme.colorScheme.primary)
            .padding(15.dp),
        tint = MaterialTheme.colorScheme.onPrimary
    )
}

@Composable
fun SimpleIcon(
    icon: ImageVector,
    size: Dp,
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    SimpleIcon(
        painter = rememberVectorPainter(image = icon),
        size = size,
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun AppLightIcon(size: Dp, modifier: Modifier = Modifier) {
    SimpleIcon(painter = painterResource(id = R.drawable.app_icon_white), size = size, modifier)
}

@Composable
fun AppPrimaryIcon(size: Dp, modifier: Modifier = Modifier) {
    SimpleIcon(painter = painterResource(id = R.drawable.app_icon_white), size = size, modifier)
}

@Preview(showBackground = false)
@Composable
fun PreviewIcons() {

    AppTheme {
        Column {
            LightIcon(painter = painterResource(id = R.drawable.water_drops))
            DarkIcon(painter = painterResource(id = R.drawable.water_drops))
        }
    }
}


