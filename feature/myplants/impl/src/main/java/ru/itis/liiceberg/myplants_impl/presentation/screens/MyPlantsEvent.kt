package ru.itis.liiceberg.myplants_impl.presentation.screens

import ru.itis.liiceberg.ui.model.UiEvent

sealed class MyPlantsEvent : UiEvent {
    data class RemovePlant(val id: String) : MyPlantsEvent()
    data class AddReminder(val id: String) : MyPlantsEvent()
}