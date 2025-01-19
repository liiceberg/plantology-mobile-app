package ru.itis.liiceberg.auth_impl.screens.sign_in

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.liiceberg.auth_api.domain.AuthInteractor
import ru.itis.liiceberg.auth_impl.screens.sign_up.SignUpAction
import ru.itis.liiceberg.common.validation.Validator
import ru.itis.liiceberg.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val validator: Validator,
    private val authInteractor: AuthInteractor
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
                if (validateAll()) {

                }
            }
        }
    }

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            runCatching {
                authInteractor.login(email, password)
            }.onSuccess {
                viewAction = SignInAction.GoToMainPage
            }.onFailure {

            }
        }
    }

    private fun validateAll() : Boolean {
        return validateEmail(viewState.email) && validatePassword(viewState.password)
    }

    private fun validateEmail(email: String) : Boolean {
        with(validator.validateEmail(email)) {
            viewState = viewState.copy(
                email = email,
                emailValidation = this
            )
            return isValid
        }
    }

    private fun validatePassword(password: String) : Boolean {
        with(validator.validatePasswordNotBlank(password)) {
            viewState = viewState.copy(
                password = password,
                passwordValidation = this
            )
            return isValid
        }
    }

}