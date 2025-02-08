package ru.itis.liiceberg.settings_impl.presentation.screens.change_password

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.itis.liiceberg.common.util.showShortToast
import ru.itis.liiceberg.settings_impl.R
import ru.itis.liiceberg.ui.components.DarkTopAppBar
import ru.itis.liiceberg.ui.components.ErrorMessage
import ru.itis.liiceberg.ui.components.LoadingView
import ru.itis.liiceberg.ui.components.PasswordTextField
import ru.itis.liiceberg.ui.components.SimpleButton
import ru.itis.liiceberg.ui.model.LoadState
import ru.itis.liiceberg.ui.theme.AppTheme

@Composable
fun ChangePasswordView(
    viewModel: ChangePasswordViewModel = hiltViewModel(),
    goBack: () -> Unit,
) {
    val state by viewModel.viewStates().collectAsStateWithLifecycle()

    ChangePasswordView(
        state = state,
        onCurrentPasswordFilled = {
            viewModel.obtainEvent(ChangePasswordEvent.OnCurrentPasswordFilled(it))
        },
        onNewPasswordFilled = {
            viewModel.obtainEvent(ChangePasswordEvent.OnNewPasswordFilled(it))
        },
        onNewPasswordConfirmFilled = {
            viewModel.obtainEvent(ChangePasswordEvent.OnConfirmNewPasswordFilled(it))
        },
        onConfirm = {
            viewModel.obtainEvent(ChangePasswordEvent.OnConfirm)
        },
        goBack = goBack,
    )

    val ctx = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.viewActions().collect { action ->
            when (action) {
                is ChangePasswordAction.ShowSuccessResult -> {
                    ctx.showShortToast(R.string.change_password_success_message)
                }

                else -> {}
            }
        }
    }
}

@Composable
private fun ChangePasswordView(
    state: ChangePasswordState,
    onCurrentPasswordFilled: (password: String) -> Unit,
    onNewPasswordFilled: (password: String) -> Unit,
    onNewPasswordConfirmFilled: (password: String) -> Unit,
    onConfirm: () -> Unit,
    goBack: () -> Unit,
) {
    with(state) {
        Box(Modifier.fillMaxSize()) {
            Scaffold(
                topBar = {
                    DarkTopAppBar(
                        title = stringResource(R.string.change_password_top_bar_text),
                        onNavigate = goBack
                    )
                },
            ) { innerPadding ->

                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        PasswordTextField(
                            value = currentPassword,
                            label = stringResource(id = R.string.current_password_text_field),
                            supportingText = currentPasswordValidation.error,
                        ) {
                            onCurrentPasswordFilled.invoke(it)
                        }
                        PasswordTextField(
                            value = newPassword,
                            label = stringResource(id = R.string.new_password_text_field),
                            supportingText = newPasswordValidation.error,
                        ) {
                            onNewPasswordFilled.invoke(it)
                        }
                        PasswordTextField(
                            value = confirmNewPassword,
                            label = stringResource(id = R.string.confirm_new_password_text_field),
                            supportingText = confirmNewPasswordValidation.error,
                        ) {
                            onNewPasswordConfirmFilled.invoke(it)
                        }
                    }
                    SimpleButton(
                        text = stringResource(R.string.confirm_button),
                        modifier = Modifier.padding(top = 24.dp),
                        enabled = currentPasswordValidation.isValid
                                && newPasswordValidation.isValid
                                && confirmNewPasswordValidation.isValid
                    ) {
                        onConfirm.invoke()
                    }
                }
            }

            when (loadState) {
                is LoadState.Error -> ErrorMessage(errorText = loadState.message)
                LoadState.Loading -> LoadingView()
                else -> {}
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChangePasswordPreview() {
    AppTheme {
        ChangePasswordView(ChangePasswordState(), {}, {}, {}, {}, {})
    }
}