package ru.itis.liiceberg.myplants_impl.screens

import ru.itis.liiceberg.myplants_impl.model.MyPlant
import ru.itis.liiceberg.ui.model.UiState

data class MyPlantsState(
    val myPlants: List<MyPlant> = emptyList()
) : UiState
