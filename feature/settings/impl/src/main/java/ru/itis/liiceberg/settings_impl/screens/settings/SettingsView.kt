package ru.itis.liiceberg.settings_impl.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.itis.liiceberg.settings_impl.R
import ru.itis.liiceberg.ui.components.BodySmallText
import ru.itis.liiceberg.ui.components.DarkTopAppBar
import ru.itis.liiceberg.ui.components.SimpleIconButton
import ru.itis.liiceberg.ui.components.TitleSmallText
import ru.itis.liiceberg.ui.theme.AppTheme

@Composable
fun SettingsView(
    viewModel: SettingsViewModel = hiltViewModel(),
    toChangePassword: () -> Unit,
    onLogOut: () -> Unit,
    onBack: () -> Unit,
) {
    val state by viewModel.viewStates().collectAsStateWithLifecycle()

    SettingsView(
        state,
        toChangePassword,
        onLogOut,
        onBack
    )

    LaunchedEffect(Unit) {
        viewModel.viewActions().collect { action ->
            when (action) {
                else -> {}
            }
        }
    }
}

@Composable
private fun SettingsView(
    state: SettingsState,
    onChangePassword: () -> Unit,
    onLogOut: () -> Unit,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            DarkTopAppBar(
                title = stringResource(R.string.settings_top_bar_text),
                onNavigate = onBack
            )
        },
    ) { innerPadding ->
        with(state) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column {
                    SettingsItem(stringResource(R.string.email), value = email)
                    SettingsItem(stringResource(R.string.username), value = username)
                }
                SettingsItem(stringResource(R.string.change_password), onClick = onChangePassword)
                SettingsItem(
                    stringResource(R.string.log_out),
                    additionalInfo = email,
                    onClick = onLogOut
                )
            }
        }
    }

}

@Composable
private fun SettingsItem(
    name: String,
    value: String? = null,
    additionalInfo: String? = null,
    onClick: (() -> Unit)? = null
) {

    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                TitleSmallText(text = name)
                additionalInfo?.let { BodySmallText(text = additionalInfo) }
            }
            Row(
                modifier = if (onClick != null) Modifier else Modifier.padding(end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                value?.let {
                    TitleSmallText(
                        text = value,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
                onClick?.let {
                    SimpleIconButton(
                        icon = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        size = 12.dp,
                        onClick = onClick
                    )
                }
            }
        }
        HorizontalDivider()
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsPreview() {
    AppTheme {
        Column {
            SettingsView(SettingsState("a@gmail.com", "Aisylu"), {}, {}, {})
        }

    }
}