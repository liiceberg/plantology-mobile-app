package ru.itis.liiceberg.settings_impl.presentation.screens.change_password

import androidx.compose.runtime.Immutable
import ru.itis.liiceberg.ui.model.UiState
import ru.itis.liiceberg.common.validation.ValidationResult

@Immutable
data class ChangePasswordState(
    val currentPassword: String = "",
    val currentPasswordValidation: ValidationResult = ValidationResult.empty(),
    val newPassword: String = "",
    val newPasswordValidation: ValidationResult = ValidationResult.empty(),
    val confirmNewPassword: String = "",
    val confirmNewPasswordValidation: ValidationResult = ValidationResult.empty(),
) : UiState