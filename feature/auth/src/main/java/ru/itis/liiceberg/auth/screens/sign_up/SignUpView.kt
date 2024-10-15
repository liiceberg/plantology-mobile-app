package ru.itis.liiceberg.auth.screens.sign_up

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.itis.liiceberg.auth.R
import ru.itis.liiceberg.common.validation.ValidationResult
import ru.itis.liiceberg.ui.components.AppPrimaryIcon
import ru.itis.liiceberg.ui.components.BodyText
import ru.itis.liiceberg.ui.components.BodyTextWithLink
import ru.itis.liiceberg.ui.components.PasswordTextField
import ru.itis.liiceberg.ui.components.SimpleButton
import ru.itis.liiceberg.ui.components.SimpleButtonWithStartIcon
import ru.itis.liiceberg.ui.components.SimpleIconButton
import ru.itis.liiceberg.ui.components.SimpleTextField
import ru.itis.liiceberg.ui.components.TitleText
import ru.itis.liiceberg.ui.theme.AppTheme

@Composable
fun SignUpView(
    viewModel: SignUpViewModel = hiltViewModel(),
    toSignIn: () -> Unit
) {
    val state by viewModel.viewStates().collectAsStateWithLifecycle()

    SignUpView(
        username = state.username,
        email = state.email,
        password = state.password,
        confirmPassword = state.confirmPassword,
        usernameValidation = state.usernameValidation,
        emailValidation = state.emailValidation,
        passwordValidation = state.passwordValidation,
        confirmPasswordValidation = state.confirmPasswordValidation,
        onUsernameFilled = { viewModel.obtainEvent(SignUpEvent.OnUsernameFilled(it)) },
        onEmailFilled = { viewModel.obtainEvent(SignUpEvent.OnEmailFilled(it)) },
        onPasswordFilled = { viewModel.obtainEvent(SignUpEvent.OnPasswordFilled(it)) },
        onConfirmPasswordFilled = { viewModel.obtainEvent(SignUpEvent.OnConfirmPasswordFilled(it)) },
        onSignUpClicked = { viewModel.obtainEvent(SignUpEvent.OnSignUp) },
        onSignUpWithGoogleClicked = { viewModel.obtainEvent(SignUpEvent.OnSignUpWithGoogle) },
        toSignIn = toSignIn,
    )
}

@Composable
private fun SignUpView(
    username: String,
    email: String,
    password: String,
    confirmPassword: String,
    usernameValidation: ValidationResult,
    emailValidation: ValidationResult,
    passwordValidation: ValidationResult,
    confirmPasswordValidation: ValidationResult,
    onUsernameFilled: (username: String) -> Unit,
    onEmailFilled: (email: String) -> Unit,
    onPasswordFilled: (password: String) -> Unit,
    onConfirmPasswordFilled: (password: String) -> Unit,
    onSignUpClicked: () -> Unit,
    onSignUpWithGoogleClicked: () -> Unit,
    toSignIn: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SimpleIconButton(
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                size = 24.dp
            ) {
                toSignIn()
            }
            AppPrimaryIcon(28.dp)
        }

        TitleText(
            text = stringResource(id = R.string.sign_up_title_text),
            Modifier.padding(top = 72.dp)
        )
        SimpleTextField(
            value = username,
            label = stringResource(id = R.string.username_label),
            supportingText = usernameValidation.error,
            modifier = Modifier.padding(top = 72.dp)
        ) {
            onUsernameFilled(it)
        }

        SimpleTextField(
            value = email,
            label = stringResource(id = R.string.email_label),
            supportingText = emailValidation.error
        ) {
            onEmailFilled(it)
        }
        PasswordTextField(
            value = password,
            label = stringResource(id = R.string.password_label),
            supportingText = passwordValidation.error
        ) {
            onPasswordFilled(it)
        }
        PasswordTextField(
            value = confirmPassword,
            label = stringResource(id = R.string.confirm_password_label),
            supportingText = confirmPasswordValidation.error
        ) {
            onConfirmPasswordFilled(it)
        }

        val enableButtons = usernameValidation.isValid && emailValidation.isValid
                && passwordValidation.isValid && confirmPasswordValidation.isValid

        SimpleButton(
            text = stringResource(id = R.string.sign_up),
            modifier = Modifier.padding(top = 32.dp),
            enabled = enableButtons,
        ) {
            onSignUpClicked()
        }
        BodyText(
            text = stringResource(id = R.string.or),
            modifier = Modifier.padding(vertical = 4.dp)
        )
        SimpleButtonWithStartIcon(
            text = stringResource(id = R.string.sign_up_with_google),
            icon = R.drawable.icons8_google,
            enabled = enableButtons,
        ) {
            onSignUpWithGoogleClicked()
        }
        BodyTextWithLink(
            stringResource(id = R.string.have_account),
            stringResource(id = R.string.sign_in),
            modifier = Modifier.padding(top = 48.dp)
        ) {
            toSignIn()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SigUpPreview() {
    AppTheme {
        SignUpView("", "", "", "", ValidationResult.empty(), ValidationResult.empty(), ValidationResult.empty(), ValidationResult.empty(), {}, {}, {}, {}, {}, {}, {})
    }
}