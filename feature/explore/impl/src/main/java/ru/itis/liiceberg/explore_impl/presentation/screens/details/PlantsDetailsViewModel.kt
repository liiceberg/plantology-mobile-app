package ru.itis.liiceberg.explore_impl.presentation.screens.details

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.liiceberg.common.exceptions.ExceptionHandlerDelegate
import ru.itis.liiceberg.common.exceptions.runCatching
import ru.itis.liiceberg.explore_impl.domain.usecase.AddFavouriteUseCase
import ru.itis.liiceberg.explore_impl.domain.usecase.GetPlantByIdUseCase
import ru.itis.liiceberg.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class PlantsDetailsViewModel @Inject constructor(
    private val getPlantByIdUseCase: GetPlantByIdUseCase,
    private val addFavouriteUseCase: AddFavouriteUseCase,
    private val exceptionHandler: ExceptionHandlerDelegate,
) : BaseViewModel<PlantsDetailsState, PlantsDetailsEvent, PlantsDetailsAction>(PlantsDetailsState()) {

    fun addFavourite(plantId: String) {
        viewModelScope.launch {
            runCatching(exceptionHandler) {
                addFavouriteUseCase.invoke(plantId)
            }.onSuccess {
                val newPlantModel = viewState.plantModel?.copy(saved = true)
                viewState = viewState.copy(plantModel = newPlantModel)
                viewAction = PlantsDetailsAction.ShowSuccessAddToFavoriteMessage
            }.onFailure { ex ->
                showError(ex.message)
            }
        }
    }

    fun loadInfo(id: String) {
        viewModelScope.launch {
            runCatching(exceptionHandler) {
                getPlantByIdUseCase.invoke(id)
            }.onSuccess {
                viewState = viewState.copy(
                    plantModel = it
                )
            }.onFailure { ex ->
                showError(ex.message)
            }
        }
    }

}