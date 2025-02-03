package ru.itis.liiceberg.auth_impl.screens.sign_in

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import ru.itis.liiceberg.common.validation.ValidationResult
import ru.itis.liiceberg.ui.components.AppLightIcon
import ru.itis.liiceberg.ui.components.BodyMediumText
import ru.itis.liiceberg.ui.components.BodyTextWithLink
import ru.itis.liiceberg.ui.components.HeadlineLargeText
import ru.itis.liiceberg.ui.components.PasswordTextField
import ru.itis.liiceberg.ui.components.SimpleButton
import ru.itis.liiceberg.ui.components.SimpleButtonWithStartIcon
import ru.itis.liiceberg.ui.components.SimpleTextField
import ru.itis.liiceberg.ui.theme.AppTheme

@Composable
fun SignInView(
    viewModel: SignInViewModel = hiltViewModel(),
    toSignUp: () -> Unit,
    toMainPage: () -> Unit,
) {
    val state by viewModel.viewStates().collectAsStateWithLifecycle()

    SignInView(
        email = state.email,
        password = state.password,
        emailValidation = state.emailValidation,
        passwordValidation = state.passwordValidation,
        onEmailFilled = { viewModel.obtainEvent(SignInEvent.OnEmailFilled(it)) },
        onPasswordFilled = { viewModel.obtainEvent(SignInEvent.OnPasswordFilled(it)) },
        onSignInClicked = { viewModel.obtainEvent(SignInEvent.OnSignIn) },
        onSignInWithGoogleClicked = { viewModel.obtainEvent(SignInEvent.OnSignInWithGoogle) },
        toSignUp = toSignUp,
    )

    LaunchedEffect(Unit) {
        viewModel.viewActions().collect { action ->
            when(action) {
                is SignInAction.GoToMainPage -> toMainPage()
                else -> {}
            }
        }
    }
}

@Composable
private fun SignInView(
    email: String,
    password: String,
    emailValidation: ValidationResult,
    passwordValidation: ValidationResult,
    onEmailFilled: (email: String) -> Unit,
    onPasswordFilled: (password: String) -> Unit,
    onSignInClicked: () -> Unit,
    onSignInWithGoogleClicked: () -> Unit,
    toSignUp: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppLightIcon(72.dp, Modifier.padding(top = 72.dp))
        HeadlineLargeText(
            text = stringResource(id = R.string.sign_in_title_text),
            Modifier.padding(top = 72.dp)
        )
        SimpleTextField(
            value = email,
            label = stringResource(id = R.string.email_label),
            supportingText = emailValidation.error,
            modifier = Modifier.padding(top = 72.dp)
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

        val enableButtons = emailValidation.isValid && passwordValidation.isValid

        SimpleButton(
            text = stringResource(id = R.string.sign_in),
            enabled = enableButtons,
            modifier = Modifier.padding(top = 32.dp)
        ) {
            onSignInClicked()
        }
        BodyMediumText(
            text = stringResource(id = R.string.or),
            modifier = Modifier.padding(vertical = 4.dp)
        )
        SimpleButtonWithStartIcon(
            text = stringResource(id = R.string.sign_in_with_google),
            icon = painterResource(id = R.drawable.icons8_google),
            enabled = enableButtons
        ) {
            onSignInWithGoogleClicked()
        }
        BodyTextWithLink(
            stringResource(id = R.string.no_account),
            stringResource(id = R.string.sign_up),
            modifier = Modifier.padding(top = 48.dp)
        ) {
            toSignUp()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInPreview() {
    AppTheme {
        SignInView("", "", ValidationResult.empty(), ValidationResult.empty(), {}, {}, {}, {}, {})
    }
}