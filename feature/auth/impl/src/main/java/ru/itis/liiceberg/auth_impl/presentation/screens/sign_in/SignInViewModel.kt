package ru.itis.liiceberg.auth_impl.presentation.screens.sign_in

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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
import ru.itis.liiceberg.ui.model.LoadState
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
                viewState = viewState.copy(loadState = LoadState.Loading)
                loginUseCase.invoke(email, password)
            }.onSuccess {
                viewState = viewState.copy(loadState = LoadState.Success)
                viewAction = SignInAction.GoToMainPage
            }.onFailure { ex ->
                if (ex is AppException.InvalidCredentials) {
                    viewState = viewState.copy(loadState = LoadState.Success)
                    viewState = viewState.copy(
                        passwordValidation = ValidationResult(
                            isValid = false,
                            error = resManager.getString(R.string.invalid_credentials_error)
                        )
                    )
                } else {
                    ex.message?.let { viewState = viewState.copy(loadState = LoadState.Error(it)) }
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