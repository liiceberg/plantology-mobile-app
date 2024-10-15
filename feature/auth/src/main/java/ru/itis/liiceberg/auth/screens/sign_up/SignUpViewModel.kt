package ru.itis.liiceberg.auth.screens.sign_up

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.itis.liiceberg.common.validation.Validator
import ru.itis.liiceberg.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val validator: Validator
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
                validateAll()
            }

            is SignUpEvent.OnSignUpWithGoogle -> {
                validateAll()
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