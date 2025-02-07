package ru.itis.liiceberg.auth_impl.presentation.screens.sign_in

import ru.itis.liiceberg.ui.model.UiEvent

sealed interface SignInEvent : UiEvent {
    data class OnEmailFilled(val email: String) : SignInEvent
    data class OnPasswordFilled(val password: String) : SignInEvent
    data object OnSignIn : SignInEvent
    data object OnSignInWithGoogle : SignInEvent
}