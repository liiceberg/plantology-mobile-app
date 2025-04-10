package ru.itis.liiceberg.explore_impl.presentation.screens.explore

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.itis.liiceberg.common.exceptions.ExceptionHandlerDelegate
import ru.itis.liiceberg.common.exceptions.runCatching
import ru.itis.liiceberg.explore_api.domain.model.ExplorePlantModel
import ru.itis.liiceberg.explore_impl.domain.usecase.GetPlantsUseCase
import ru.itis.liiceberg.ui.base.BaseViewModel
import ru.itis.liiceberg.ui.model.LoadState
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val getPlantsUseCase: GetPlantsUseCase,
    private val exceptionHandler: ExceptionHandlerDelegate,
) : BaseViewModel<ExploreState, ExploreEvent, ExploreAction>(ExploreState()) {

    private val allPlants = mutableListOf<ExplorePlantModel>()

    override fun init() {
        if (allPlants.isEmpty()) getPlants()
    }

    override fun obtainEvent(event: ExploreEvent) {
        when (event) {
            is ExploreEvent.OnSearchFieldFilled -> {
                val results = filterSearchResults(event.input.trim())
                viewState = viewState.copy(
                    searchQuery = event.input,
                    searchResult = results,
                )
            }

            is ExploreEvent.OnSearch -> {
                applySearchResults()
            }
        }
    }

    override fun onError(message: String) {
        viewModelScope.launch {
            viewState = viewState.copy(loadState = LoadState.Error(message))
            delay(3_000)
            viewState = viewState.copy(loadState = LoadState.Success)
        }
    }

    private fun getPlants() {
        viewModelScope.launch {
            runCatching(exceptionHandler) {
                viewState = viewState.copy(loadState = LoadState.Loading)
                getPlantsUseCase.invoke()
            }.onSuccess {
                viewState = viewState.copy(loadState = LoadState.Success, items = it)
                allPlants.addAll(it)
            }.onFailure { ex ->
                ex.message?.let { onError(it) }
            }
        }
    }

    private fun filterSearchResults(query: String): List<ExplorePlantModel> {
        return if (query.isEmpty()) {
            emptyList()
        } else {
            allPlants.filter { plant ->
                plant.name.lowercase().startsWith(query.lowercase())
                        || plant.scientificName.lowercase().startsWith(query.lowercase())
            }
        }
    }

    private fun applySearchResults() {
        val items = if (viewState.searchQuery.isBlank()) allPlants else viewState.searchResult
        viewState = viewState.copy(items = items)
    }

}