package ru.itis.liiceberg.auth_impl.screens.sign_up

import ru.itis.liiceberg.ui.model.UiEvent

sealed interface SignUpEvent : UiEvent {
    data class OnUsernameFilled(val username: String) : SignUpEvent
    data class OnEmailFilled(val email: String) : SignUpEvent
    data class OnPasswordFilled(val password: String) : SignUpEvent
    data class OnConfirmPasswordFilled(val password: String) : SignUpEvent
    data object OnSignUp : SignUpEvent
    data object OnSignUpWithGoogle : SignUpEvent
}