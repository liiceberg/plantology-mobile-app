package ru.itis.liiceberg.reminder_impl.presentation.screens

import ru.itis.liiceberg.ui.model.UiEvent

sealed class ReminderEvent : UiEvent {
    data class OnTabSelected(val index: Int) : ReminderEvent()
}