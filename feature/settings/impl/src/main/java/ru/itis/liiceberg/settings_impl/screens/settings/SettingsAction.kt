package ru.itis.liiceberg.settings_impl.screens.settings

import ru.itis.liiceberg.ui.model.UiAction

sealed class SettingsAction : UiAction {
    data object GoToChangePassword: SettingsAction()
    data object GoToSignIn: SettingsAction()
}