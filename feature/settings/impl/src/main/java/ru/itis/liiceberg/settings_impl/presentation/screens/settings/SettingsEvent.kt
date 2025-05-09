package ru.itis.liiceberg.settings_impl.presentation.screens.settings

import ru.itis.liiceberg.ui.model.UiEvent

sealed interface SettingsEvent : UiEvent {
    data object ScreenOpened: SettingsEvent
    data object OnLogout: SettingsEvent
}