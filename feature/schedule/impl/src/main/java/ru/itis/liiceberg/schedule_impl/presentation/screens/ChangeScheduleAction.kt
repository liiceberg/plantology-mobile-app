package ru.itis.liiceberg.schedule_impl.presentation.screens

import ru.itis.liiceberg.ui.model.UiAction

sealed interface ChangeScheduleAction : UiAction {
    data object ShowSuccessSaveToast : ChangeScheduleAction
}