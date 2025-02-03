package ru.itis.liiceberg.explore_impl.screens.explore

import ru.itis.liiceberg.explore_api.domain.model.ExplorePlantModel
import ru.itis.liiceberg.ui.model.UiState

data class ExploreState(
    val items: List<ExplorePlantModel> = emptyList()
) : UiState