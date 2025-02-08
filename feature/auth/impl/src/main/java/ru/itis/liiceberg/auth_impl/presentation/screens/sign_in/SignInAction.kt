package ru.itis.liiceberg.auth_impl.presentation.screens.sign_in

import ru.itis.liiceberg.ui.model.UiAction

sealed class SignInAction : UiAction {
    data object GoToMainPage: SignInAction()
}