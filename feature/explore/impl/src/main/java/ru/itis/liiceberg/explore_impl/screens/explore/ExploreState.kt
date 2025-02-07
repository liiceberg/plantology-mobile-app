package ru.itis.liiceberg.explore_impl.screens.explore

import androidx.compose.runtime.Immutable
import ru.itis.liiceberg.explore_api.domain.model.ExplorePlantModel
import ru.itis.liiceberg.ui.model.UiState

@Immutable
data class ExploreState(
    val items: List<ExplorePlantModel> = emptyList(),
) : UiState