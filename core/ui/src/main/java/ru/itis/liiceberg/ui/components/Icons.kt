package ru.itis.liiceberg.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import ru.itis.liiceberg.ui.R

@Composable
fun SimpleIcon(@DrawableRes icon: Int, size: Dp, modifier: Modifier = Modifier) {
    Icon(
        painter = painterResource(id = icon),
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
    SimpleIcon(icon = R.drawable.app_icon_white, size = size, modifier)
}

@Composable
fun AppPrimaryIcon(size: Dp, modifier: Modifier = Modifier) {
    SimpleIcon(icon = R.drawable.app_icon_primary, size = size, modifier)
}
