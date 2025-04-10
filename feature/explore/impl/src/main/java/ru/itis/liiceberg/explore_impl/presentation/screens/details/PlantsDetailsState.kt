package ru.itis.liiceberg.explore_impl.presentation.screens.details

import androidx.compose.runtime.Immutable
import ru.itis.liiceberg.explore_api.domain.model.PlantModel
import ru.itis.liiceberg.ui.model.LoadState
import ru.itis.liiceberg.ui.model.UiState

@Immutable
data class PlantsDetailsState(
    val plantModel: PlantModel? = null,
    val loadState: LoadState = LoadState.Initial,
) : UiState