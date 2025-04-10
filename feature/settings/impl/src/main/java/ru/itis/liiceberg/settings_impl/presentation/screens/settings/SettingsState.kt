package ru.itis.liiceberg.settings_impl.presentation.screens.settings

import androidx.compose.runtime.Immutable
import ru.itis.liiceberg.ui.model.LoadState
import ru.itis.liiceberg.ui.model.UiState

@Immutable
data class SettingsState(
    val email: String = "",
    val username: String = "",
    val loadState: LoadState = LoadState.Initial,
) : UiState