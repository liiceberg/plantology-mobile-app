package ru.itis.liiceberg.auth_impl.presentation.screens.sign_up

import ru.itis.liiceberg.ui.model.UiAction

sealed class SignUpAction : UiAction {
    data object RedirectOnSuccess : SignUpAction()
}