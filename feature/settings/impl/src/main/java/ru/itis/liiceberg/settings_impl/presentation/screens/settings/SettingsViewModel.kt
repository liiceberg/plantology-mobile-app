package ru.itis.liiceberg.settings_impl.presentation.screens.settings

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.liiceberg.common.exceptions.ExceptionHandlerDelegate
import ru.itis.liiceberg.common.exceptions.runCatching
import ru.itis.liiceberg.settings_impl.domain.usecase.GetUserInfoUseCase
import ru.itis.liiceberg.settings_impl.domain.usecase.LogOutUseCase
import ru.itis.liiceberg.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val exceptionHandler: ExceptionHandlerDelegate,
) : BaseViewModel<SettingsState, SettingsEvent, SettingsAction>(
    SettingsState()
) {

    override fun init() {
        loadUserInfo()
    }

    fun logout() {
        viewModelScope.launch {
            runCatching(exceptionHandler) {
                logOutUseCase.invoke()
            }.onSuccess {
                viewAction = SettingsAction.GoToSignIn
            }.onFailure { ex ->
                showError(ex.message)
            }
        }
    }

    private fun loadUserInfo() {
        viewModelScope.launch {
            runCatching(exceptionHandler) {
                getUserInfoUseCase.invoke()
            }.onSuccess {
                viewState = viewState.copy(
                    username = it.username,
                    email = it.email
                )
            }.onFailure { ex ->
                showError(ex.message)
            }
        }
    }

}