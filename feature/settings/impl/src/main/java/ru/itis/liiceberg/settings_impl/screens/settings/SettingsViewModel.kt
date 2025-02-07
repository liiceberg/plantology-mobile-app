package ru.itis.liiceberg.settings_impl.screens.settings

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.liiceberg.settings_api.domain.usecase.GetUserInfoUseCase
import ru.itis.liiceberg.settings_api.domain.usecase.LogOutUseCase
import ru.itis.liiceberg.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val logOutUseCase: LogOutUseCase,
) : BaseViewModel<SettingsState, SettingsEvent, SettingsAction>(
    SettingsState()
) {

    override fun init() {
        loadUserInfo()
    }

    fun logout() {
        viewModelScope.launch {
            runCatching {
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
            runCatching {
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