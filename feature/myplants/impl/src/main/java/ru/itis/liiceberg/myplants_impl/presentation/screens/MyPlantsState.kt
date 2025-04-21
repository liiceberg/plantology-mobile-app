package ru.itis.liiceberg.myplants_impl.presentation.screens

import androidx.compose.runtime.Immutable
import ru.itis.liiceberg.myplants_api.presenatation.MyPlantUiModel
import ru.itis.liiceberg.ui.model.LoadState
import ru.itis.liiceberg.ui.model.UiState

@Immutable
data class MyPlantsState(
    val myPlants: List<MyPlantUiModel> = emptyList(),
    val loadState: LoadState = LoadState.Initial,
) : UiState
