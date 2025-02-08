package ru.itis.liiceberg.settings_impl.presentation.screens.settings

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.itis.liiceberg.common.exceptions.ExceptionHandlerDelegate
import ru.itis.liiceberg.common.exceptions.runCatching
import ru.itis.liiceberg.settings_impl.domain.usecase.GetUserInfoUseCase
import ru.itis.liiceberg.settings_impl.domain.usecase.LogOutUseCase
import ru.itis.liiceberg.ui.base.BaseViewModel
import ru.itis.liiceberg.ui.model.LoadState
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
                ex.message?.let { onError(it) }
            }
        }
    }

    override fun onError(message: String) {
        viewModelScope.launch {
            viewState = viewState.copy(loadState = LoadState.Error(message))
            delay(3_000)
            viewState = viewState.copy(loadState = LoadState.Success)
        }
    }

    private fun loadUserInfo() {
        viewModelScope.launch {
            runCatching(exceptionHandler) {
                viewState = viewState.copy(loadState = LoadState.Loading)
                getUserInfoUseCase.invoke()
            }.onSuccess {
                viewState = viewState.copy(loadState = LoadState.Success)
                viewState = viewState.copy(
                    username = it.username,
                    email = it.email
                )
            }.onFailure { ex ->
                ex.message?.let { onError(it) }
            }
        }
    }

}