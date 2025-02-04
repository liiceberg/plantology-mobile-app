package ru.itis.liiceberg.settings_impl.screens.change_password

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.liiceberg.common.validation.ValidationResult
import ru.itis.liiceberg.common.validation.Validator
import ru.itis.liiceberg.settings_api.domain.usecase.ChangePasswordUseCase
import ru.itis.liiceberg.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val validator: Validator,
    private val changePasswordUseCase: ChangePasswordUseCase,
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
            runCatching {
                changePasswordUseCase.invoke()
            }.onSuccess {
                viewAction = ChangePasswordAction.ShowPasswordChangedResults(true)
            }.onFailure {
                viewAction = ChangePasswordAction.ShowPasswordChangedResults(false)
            }
        }
    }


    private fun validateCurrentPassword(password: String): Boolean {
        viewState = viewState.copy(
            currentPassword = password,
            currentPasswordValidation = ValidationResult.empty()
        )
        return true
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
        return validateCurrentPassword(viewState.currentPassword)
                && validateNewPassword(viewState.newPassword)
                && validateConfirmNewPassword(viewState.confirmNewPassword)
    }
}