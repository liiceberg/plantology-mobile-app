package ru.itis.liiceberg.auth.screens.sign_in

import ru.itis.liiceberg.ui.model.UiState
import ru.itis.liiceberg.common.validation.ValidationResult

data class SignInState(
    val email: String = "",
    val emailValidation: ValidationResult = ValidationResult.empty(),
    val password: String = "",
    val passwordValidation: ValidationResult = ValidationResult.empty(),
) : UiState