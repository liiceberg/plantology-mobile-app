package ru.itis.liiceberg.schedule_impl.presentation.screens

import ru.itis.liiceberg.ui.model.UiEvent

sealed interface ChangeScheduleEvent : UiEvent {
    data object OnSave : ChangeScheduleEvent
}