package ru.itis.liiceberg.auth_impl.presentation.screens.sign_in

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.liiceberg.auth_impl.domain.usecase.LoginUseCase
import ru.itis.liiceberg.auth_impl.R
import ru.itis.liiceberg.common.resources.ResourceManager
import ru.itis.liiceberg.common.validation.UserDataValidator
import ru.itis.liiceberg.common.validation.ValidationResult
import ru.itis.liiceberg.common.exceptions.AppException
import ru.itis.liiceberg.common.exceptions.ExceptionHandlerDelegate
import ru.itis.liiceberg.common.exceptions.runCatching
import ru.itis.liiceberg.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val validator: UserDataValidator,
    private val loginUseCase: LoginUseCase,
    private val exceptionHandler: ExceptionHandlerDelegate,
    private val resManager: ResourceManager,
) : BaseViewModel<SignInState, SignInEvent, SignInAction>(
    SignInState()
) {

    override fun obtainEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.OnEmailFilled -> {
                validateEmail(event.email)
            }

            is SignInEvent.OnPasswordFilled -> {
                validatePassword(event.password)
            }

            is SignInEvent.OnSignIn -> {
                if (validateAll()) {
                    login(viewState.email, viewState.password)
                }
            }

            is SignInEvent.OnSignInWithGoogle -> {
            }
        }
    }

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            runCatching(exceptionHandler) {
                loginUseCase.invoke(email, password)
            }.onSuccess {
                viewAction = SignInAction.GoToMainPage
            }.onFailure { ex ->
                if (ex is AppException.InvalidCredentials) {
                    viewState = viewState.copy(
                        emailValidation = ValidationResult(
                            isValid = false,
                            error = resManager.getString(R.string.invalid_credentials_error)
                        )
                    )
                } else {
                    showError(ex.message)
                }
            }
        }
    }

    private fun validateAll(): Boolean {
        return validateEmail(viewState.email) && validatePassword(viewState.password)
    }

    private fun validateEmail(email: String): Boolean {
        with(validator.validateEmail(email)) {
            viewState = viewState.copy(
                email = email,
                emailValidation = this
            )
            return isValid
        }
    }

    private fun validatePassword(password: String): Boolean {
        with(validator.validatePasswordNotBlank(password)) {
            viewState = viewState.copy(
                password = password,
                passwordValidation = this
            )
            return isValid
        }
    }

}