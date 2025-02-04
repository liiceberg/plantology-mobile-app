package ru.itis.liiceberg.settings_impl.screens.change_password

import ru.itis.liiceberg.ui.model.UiAction

sealed class ChangePasswordAction : UiAction {
    data object GoBack: ChangePasswordAction()
    data class ShowPasswordChangedResults(val success: Boolean) : ChangePasswordAction()
}