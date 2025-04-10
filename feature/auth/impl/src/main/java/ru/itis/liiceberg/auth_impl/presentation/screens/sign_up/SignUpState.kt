package ru.itis.liiceberg.auth_impl.presentation.screens.sign_up

import androidx.compose.runtime.Immutable
import ru.itis.liiceberg.common.validation.ValidationResult
import ru.itis.liiceberg.ui.model.LoadState
import ru.itis.liiceberg.ui.model.UiState

@Immutable
data class SignUpState(
    val username: String = "",
    val usernameValidation: ValidationResult = ValidationResult.empty(),
    val email: String = "",
    val emailValidation: ValidationResult = ValidationResult.empty(),
    val password: String = "",
    val passwordValidation: ValidationResult = ValidationResult.empty(),
    val confirmPassword: String = "",
    val confirmPasswordValidation: ValidationResult = ValidationResult.empty(),
    val loadState: LoadState = LoadState.Initial,
) : UiState