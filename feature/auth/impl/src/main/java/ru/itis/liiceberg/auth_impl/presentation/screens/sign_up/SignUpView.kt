package ru.itis.liiceberg.auth_impl.presentation.screens.sign_up

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.itis.liiceberg.auth_impl.R
import ru.itis.liiceberg.ui.components.AppPrimaryIcon
import ru.itis.liiceberg.ui.components.BodyMediumText
import ru.itis.liiceberg.ui.components.BodyTextWithLink
import ru.itis.liiceberg.ui.components.ErrorMessage
import ru.itis.liiceberg.ui.components.HeadlineLargeText
import ru.itis.liiceberg.ui.components.PasswordTextField
import ru.itis.liiceberg.ui.components.SimpleButton
import ru.itis.liiceberg.ui.components.SimpleButtonWithStartIcon
import ru.itis.liiceberg.ui.components.SimpleIconButton
import ru.itis.liiceberg.ui.components.SimpleTextField
import ru.itis.liiceberg.ui.theme.AppTheme

@Composable
fun SignUpView(
    viewModel: SignUpViewModel = hiltViewModel(),
    toSignIn: () -> Unit
) {
    val state by viewModel.viewStates().collectAsStateWithLifecycle()
    val error by viewModel.error().collectAsStateWithLifecycle()

    SignUpView(
        state = state,
        onUsernameFilled = { viewModel.obtainEvent(SignUpEvent.OnUsernameFilled(it)) },
        onEmailFilled = { viewModel.obtainEvent(SignUpEvent.OnEmailFilled(it)) },
        onPasswordFilled = { viewModel.obtainEvent(SignUpEvent.OnPasswordFilled(it)) },
        onConfirmPasswordFilled = { viewModel.obtainEvent(SignUpEvent.OnConfirmPasswordFilled(it)) },
        onSignUpClicked = { viewModel.obtainEvent(SignUpEvent.OnSignUp) },
        onSignUpWithGoogleClicked = { viewModel.obtainEvent(SignUpEvent.OnSignUpWithGoogle) },
        toSignIn = toSignIn,
        error = error,
    )

    LaunchedEffect(Unit) {
        viewModel.viewActions().collect { action ->
            when (action) {
                is SignUpAction.GoToSignIn -> toSignIn()
                else -> {}
            }
        }
    }
}

@Composable
private fun SignUpView(
    state: SignUpState,
    onUsernameFilled: (username: String) -> Unit,
    onEmailFilled: (email: String) -> Unit,
    onPasswordFilled: (password: String) -> Unit,
    onConfirmPasswordFilled: (password: String) -> Unit,
    error: String?,
    onSignUpClicked: () -> Unit,
    onSignUpWithGoogleClicked: () -> Unit,
    toSignIn: () -> Unit,
) {
    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            with(state) {
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

                HeadlineLargeText(
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
                BodyMediumText(
                    text = stringResource(id = R.string.or),
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                SimpleButtonWithStartIcon(
                    text = stringResource(id = R.string.sign_up_with_google),
                    icon = painterResource(id = R.drawable.icons8_google),
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
        error?.let {
            ErrorMessage(errorText = error)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SigUpPreview() {
    AppTheme {
        SignUpView(
            SignUpState(),
            {},
            {},
            {},
            {},
            null,
            {},
            {},
            {},
        )
    }
}