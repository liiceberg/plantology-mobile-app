package ru.itis.liiceberg.settings_impl.presentation.screens.change_password

import ru.itis.liiceberg.ui.model.UiAction

sealed class ChangePasswordAction : UiAction {
    data object ShowSuccessResult : ChangePasswordAction()
}