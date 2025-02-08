package ru.itis.liiceberg.explore_impl.presentation.screens.details

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.itis.liiceberg.common.exceptions.ExceptionHandlerDelegate
import ru.itis.liiceberg.common.exceptions.runCatching
import ru.itis.liiceberg.explore_impl.domain.usecase.AddFavouriteUseCase
import ru.itis.liiceberg.explore_impl.domain.usecase.GetPlantByIdUseCase
import ru.itis.liiceberg.ui.base.BaseViewModel
import ru.itis.liiceberg.ui.model.LoadState
import javax.inject.Inject

@HiltViewModel
class PlantsDetailsViewModel @Inject constructor(
    private val getPlantByIdUseCase: GetPlantByIdUseCase,
    private val addFavouriteUseCase: AddFavouriteUseCase,
    private val exceptionHandler: ExceptionHandlerDelegate,
) : BaseViewModel<PlantsDetailsState, PlantsDetailsEvent, PlantsDetailsAction>(PlantsDetailsState()) {

    override fun onError(message: String) {
        viewModelScope.launch {
            viewState = viewState.copy(loadState = LoadState.Error(message))
            delay(3_000)
            viewState = viewState.copy(loadState = LoadState.Success)
        }
    }

    fun addFavourite(plantId: String) {
        viewModelScope.launch {
            runCatching(exceptionHandler) {
                viewState = viewState.copy(loadState = LoadState.Loading)
                addFavouriteUseCase.invoke(plantId)
            }.onSuccess {
                viewState = viewState.copy(loadState = LoadState.Success)
                val newPlantModel = viewState.plantModel?.copy(saved = true)
                viewState = viewState.copy(plantModel = newPlantModel)
                viewAction = PlantsDetailsAction.ShowSuccessAddToFavoriteMessage
            }.onFailure { ex ->
                ex.message?.let { onError(it) }
            }
        }
    }

    fun loadInfo(id: String) {
        viewModelScope.launch {
            runCatching(exceptionHandler) {
                viewState = viewState.copy(loadState = LoadState.Loading)
                getPlantByIdUseCase.invoke(id)
            }.onSuccess {
                viewState = viewState.copy(loadState = LoadState.Success)
                viewState = viewState.copy(
                    plantModel = it
                )
            }.onFailure { ex ->
                ex.message?.let { onError(it) }
            }
        }
    }

}