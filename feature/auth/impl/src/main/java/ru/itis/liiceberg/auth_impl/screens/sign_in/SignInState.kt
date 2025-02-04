package ru.itis.liiceberg.auth_impl.screens.sign_in

import androidx.compose.runtime.Immutable
import ru.itis.liiceberg.ui.model.UiState
import ru.itis.liiceberg.common.validation.ValidationResult

@Immutable
data class SignInState(
    val email: String = "",
    val emailValidation: ValidationResult = ValidationResult.empty(),
    val password: String = "",
    val passwordValidation: ValidationResult = ValidationResult.empty(),
) : UiState