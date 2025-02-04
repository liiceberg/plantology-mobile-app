package ru.itis.liiceberg.settings_impl.screens.change_password

import ru.itis.liiceberg.ui.model.UiEvent

sealed interface ChangePasswordEvent : UiEvent {
    data class OnCurrentPasswordFilled(val password: String) : ChangePasswordEvent
    data class OnNewPasswordFilled(val password: String) : ChangePasswordEvent
    data class OnConfirmNewPasswordFilled(val password: String) : ChangePasswordEvent
    data object OnConfirm : ChangePasswordEvent
}