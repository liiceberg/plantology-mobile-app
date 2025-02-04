package ru.itis.liiceberg.settings_impl.screens.settings

import androidx.compose.runtime.Immutable
import ru.itis.liiceberg.ui.model.UiState
import ru.itis.liiceberg.common.validation.ValidationResult

@Immutable
data class SettingsState(
    val email: String = "",
    val username: String = "",
) : UiState