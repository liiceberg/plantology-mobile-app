package ru.itis.liiceberg.auth_impl.screens.sign_up

import ru.itis.liiceberg.common.validation.ValidationResult
import ru.itis.liiceberg.ui.model.UiState

data class SignUpState(
    val username: String = "",
    val usernameValidation: ValidationResult = ValidationResult.empty(),
    val email: String = "",
    val emailValidation: ValidationResult = ValidationResult.empty(),
    val password: String = "",
    val passwordValidation: ValidationResult = ValidationResult.empty(),
    val confirmPassword: String = "",
    val confirmPasswordValidation: ValidationResult = ValidationResult.empty(),
) : UiState