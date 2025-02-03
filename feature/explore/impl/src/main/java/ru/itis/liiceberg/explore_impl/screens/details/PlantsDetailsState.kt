package ru.itis.liiceberg.explore_impl.screens.details

import ru.itis.liiceberg.explore_api.domain.model.PlantModel
import ru.itis.liiceberg.ui.model.UiState

data class PlantsDetailsState(
    val plantModel: PlantModel?
) : UiState