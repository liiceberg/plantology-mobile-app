package ru.itis.liiceberg.auth_impl.screens.sign_up

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.liiceberg.auth_api.domain.usecase.RegisterUseCase
import ru.itis.liiceberg.auth_impl.R
import ru.itis.liiceberg.common.exceptions.AppException
import ru.itis.liiceberg.common.exceptions.ExceptionHandlerDelegate
import ru.itis.liiceberg.common.exceptions.runCatching
import ru.itis.liiceberg.common.resources.ResourceManager
import ru.itis.liiceberg.common.validation.UserDataValidator
import ru.itis.liiceberg.common.validation.ValidationResult
import ru.itis.liiceberg.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val validator: UserDataValidator,
    private val registerUseCase: RegisterUseCase,
    private val exceptionHandler: ExceptionHandlerDelegate,
    private val resManager: ResourceManager,
) : BaseViewModel<SignUpState, SignUpEvent, SignUpAction>(
    SignUpState()
) {

    override fun obtainEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.OnUsernameFilled -> {
                validateUsername(event.username)
            }

            is SignUpEvent.OnEmailFilled -> {
                validateEmail(event.email)
            }

            is SignUpEvent.OnPasswordFilled -> {
                validatePassword(event.password)
            }

            is SignUpEvent.OnConfirmPasswordFilled -> {
                validateConfirmPassword(event.password)
            }

            is SignUpEvent.OnSignUp -> {
                if (validateAll()) {
                    register(
                        viewState.username, viewState.email, viewState.password
                    )
                }
            }

            is SignUpEvent.OnSignUpWithGoogle -> {
            }
        }
    }

    private fun register(username: String, email: String, password: String) {
        viewModelScope.launch {
            runCatching(exceptionHandler) {
                registerUseCase.invoke(username, email, password)
            }.onSuccess {
                viewAction = SignUpAction.GoToSignIn
            }.onFailure { ex ->
                if (ex is AppException.SuchEmailAlreadyRegistered) {
                    viewState = viewState.copy(
                        emailValidation = ValidationResult(
                            isValid = false,
                            error = resManager.getString(R.string.invalid_email_error)
                        )
                    )
                } else {
                    showError(ex.message)
                }
            }
        }
    }

    private fun validateAll(): Boolean {
        return validateUsername(viewState.username)
                && validateEmail(viewState.email)
                && validatePassword(viewState.password)
                && validateConfirmPassword(viewState.confirmPassword)
    }

    private fun validateUsername(username: String): Boolean {
        with(validator.validateName(username)) {
            viewState = viewState.copy(
                username = username,
                usernameValidation = this
            )
            return isValid
        }
    }

    private fun validateEmail(email: String): Boolean {
        with(validator.validateEmail(email)) {
            viewState = viewState.copy(
                email = email,
                emailValidation = validator.validateEmail(email)
            )
            return isValid
        }
    }

    private fun validatePassword(password: String): Boolean {
        with(validator.validatePassword(password)) {
            viewState = viewState.copy(
                password = password,
                passwordValidation = this
            )
            return isValid
        }
    }

    private fun validateConfirmPassword(confirmPassword: String): Boolean {
        with(validator.validateConfirmPassword(viewState.password, confirmPassword)) {
            viewState = viewState.copy(
                confirmPassword = confirmPassword,
                confirmPasswordValidation = this
            )
            return isValid
        }
    }

}