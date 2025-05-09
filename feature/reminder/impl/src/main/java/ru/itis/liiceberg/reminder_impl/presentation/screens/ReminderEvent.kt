package ru.itis.liiceberg.reminder_impl.presentation.screens

import ru.itis.liiceberg.ui.model.UiEvent

sealed interface ReminderEvent : UiEvent {
    data object ScreenOpened: ReminderEvent
    data class OnTabSelected(val index: Int) : ReminderEvent
}