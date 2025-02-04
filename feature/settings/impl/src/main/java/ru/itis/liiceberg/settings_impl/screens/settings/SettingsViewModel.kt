package ru.itis.liiceberg.settings_impl.screens.settings

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.itis.liiceberg.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(

) : BaseViewModel<SettingsState, SettingsEvent, SettingsAction>(
    SettingsState()
) {


}