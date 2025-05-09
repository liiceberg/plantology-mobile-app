package ru.itis.liiceberg.myplants_impl.presentation.screens

import ru.itis.liiceberg.ui.model.UiEvent

sealed interface MyPlantsEvent : UiEvent {
    data class RemovePlant(val id: String) : MyPlantsEvent
    data object ScreenOpened : MyPlantsEvent
}