package ru.itis.liiceberg.myplants_impl.presentation.screens

import androidx.compose.runtime.Immutable
import ru.itis.liiceberg.myplants_api.domain.model.MyPlant
import ru.itis.liiceberg.ui.model.UiState

@Immutable
data class MyPlantsState(
    val myPlants: List<MyPlant> = emptyList()
) : UiState
