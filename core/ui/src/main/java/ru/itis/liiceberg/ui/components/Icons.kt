package ru.itis.liiceberg.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import ru.itis.liiceberg.ui.R

@Composable
fun SimpleIcon(painter: Painter, size: Dp, modifier: Modifier = Modifier) {
    Icon(
        painter = painter,
        contentDescription = null,
        modifier = modifier.size(size),
        tint = Color.Unspecified,
    )
}

@Composable
fun SimpleIcon(icon: ImageVector, size: Dp, modifier: Modifier = Modifier) {
    Icon(
        imageVector = icon,
        contentDescription = null,
        modifier = modifier.size(size),
        tint = Color.Unspecified,
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
