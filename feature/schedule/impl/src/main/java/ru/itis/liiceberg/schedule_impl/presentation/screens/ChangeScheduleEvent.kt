package ru.itis.liiceberg.schedule_impl.presentation.screens

import ru.itis.liiceberg.common.model.TimeValues
import ru.itis.liiceberg.ui.model.UiEvent

sealed interface ChangeScheduleEvent : UiEvent {
    data class ScreenOpened(val plantId: String) : ChangeScheduleEvent
    data class OnSave(val watering: TimeValues?, val fertilizer: TimeValues?) : ChangeScheduleEvent
}