package ru.itis.liiceberg.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LightTopAppBar(
    title: String,
    action: @Composable RowScope.() -> Unit = {},
    onNavigate: (() -> Unit)? = null
) {
    TopBar(
        title = title,
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary,
        action = action,
        onNavigate = onNavigate,
    )
}

@Composable
fun DarkTopAppBar(
    title: String,
    action: @Composable RowScope.() -> Unit = {},
    onNavigate: (() -> Unit)? = null
) {
    TopBar(
        title = title,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        action = action,
        onNavigate = onNavigate,
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopBar(
    title: String,
    containerColor: Color,
    contentColor: Color,
    action: @Composable RowScope.() -> Unit = {},
    onNavigate: (() -> Unit)? = null
) {
    TopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = containerColor,
            titleContentColor = contentColor,
        ),
        title = {
            HeadlineMediumText(
                text = title,
                modifier = if (onNavigate == null) Modifier else Modifier.padding(start = 24.dp)
            )
        },
        navigationIcon = {
            onNavigate?.let {
                SimpleIconButton(
                    icon = Icons.AutoMirrored.Filled.ArrowBack,
                    size = 24.dp,
                    tint = contentColor,
                    onClick = onNavigate,
                )
            }
        },
        actions = action,
    )
}

@Preview
@Composable
private fun TopBarPreview() {
    Column {
        DarkTopAppBar(title = "Settings", onNavigate = {}, action = {})
        LightTopAppBar(title = "Title")
    }
}