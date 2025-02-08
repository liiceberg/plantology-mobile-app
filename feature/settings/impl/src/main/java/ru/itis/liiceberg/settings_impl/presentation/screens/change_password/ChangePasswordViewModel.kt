package ru.itis.liiceberg.settings_impl.presentation.screens.change_password

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.itis.liiceberg.common.exceptions.ExceptionHandlerDelegate
import ru.itis.liiceberg.common.exceptions.runCatching
import ru.itis.liiceberg.common.resources.ResourceManager
import ru.itis.liiceberg.common.validation.UserDataValidator
import ru.itis.liiceberg.common.validation.ValidationResult
import ru.itis.liiceberg.settings_impl.R
import ru.itis.liiceberg.settings_impl.domain.usecase.ChangePasswordUseCase
import ru.itis.liiceberg.settings_impl.domain.usecase.VerifyCredentialsUseCase
import ru.itis.liiceberg.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val validator: UserDataValidator,
    private val verifyCredentialsUseCase: VerifyCredentialsUseCase,
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val resManager: ResourceManager,
    private val exceptionHandler: ExceptionHandlerDelegate,
) : BaseViewModel<ChangePasswordState, ChangePasswordEvent, ChangePasswordAction>(
    ChangePasswordState()
) {
    override fun obtainEvent(event: ChangePasswordEvent) {
        when (event) {
            is ChangePasswordEvent.OnCurrentPasswordFilled -> {
                validateCurrentPassword(event.password)
            }

            is ChangePasswordEvent.OnNewPasswordFilled -> {
                validateNewPassword(event.password)
            }

            is ChangePasswordEvent.OnConfirmNewPasswordFilled -> {
                validateConfirmNewPassword(event.password)
            }

            is ChangePasswordEvent.OnConfirm -> {
                if (validateAll()) {
                    changePassword()
                }
            }
        }
    }

    private fun changePassword() {
        viewModelScope.launch {
            runCatching(exceptionHandler) {
                changePasswordUseCase.invoke(viewState.newPassword)
            }.onSuccess {
                viewAction = ChangePasswordAction.ShowSuccessResult
            }.onFailure { ex ->
                showError(ex.message)
            }
        }
    }

    private fun validateCurrentPassword(password: String) {
        runBlocking {
            runCatching(exceptionHandler) {
                verifyCredentialsUseCase.invoke(password)
            }.onSuccess { isValid ->
                viewState = viewState.copy(
                    currentPassword = password,
                    currentPasswordValidation = ValidationResult(
                        isValid = isValid,
                        error = if (isValid) null else resManager.getString(R.string.invalid_current_password),
                    )
                )
            }
        }
    }

    private fun validateNewPassword(password: String): Boolean {
        with(validator.validatePassword(password)) {
            viewState = viewState.copy(
                newPassword = password,
                newPasswordValidation = this
            )
            return isValid
        }
    }

    private fun validateConfirmNewPassword(password: String): Boolean {
        with(validator.validateConfirmPassword(viewState.newPassword, password)) {
            viewState = viewState.copy(
                confirmNewPassword = password,
                confirmNewPasswordValidation = this
            )
            return isValid
        }
    }

    private fun validateAll(): Boolean {
        validateCurrentPassword(viewState.currentPassword)
        return viewState.currentPasswordValidation.isValid
                && validateNewPassword(viewState.newPassword)
                && validateConfirmNewPassword(viewState.confirmNewPassword)
    }
}