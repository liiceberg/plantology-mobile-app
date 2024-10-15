package ru.itis.liiceberg.auth.screens.sign_in

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.itis.liiceberg.common.validation.Validator
import ru.itis.liiceberg.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val validator: Validator
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
                validateAll()
            }

            is SignInEvent.OnSignInWithGoogle -> {
                validateAll()
            }
        }
    }

    private fun validateAll() : Boolean{
        return validateEmail(viewState.email) && validatePassword(viewState.password)
    }

    private fun validateEmail(email: String) : Boolean{
        with(validator.validateEmail(email)) {
            viewState = viewState.copy(
                email = email,
                emailValidation = this
            )
            return isValid
        }
    }

    private fun validatePassword(password: String) : Boolean{
        with(validator.validatePasswordNotBlank(password)) {
            viewState = viewState.copy(
                password = password,
                passwordValidation = this
            )
            return isValid
        }
    }

}