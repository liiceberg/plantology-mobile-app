package ru.itis.liiceberg.explore_impl.screens.details

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.liiceberg.explore_api.domain.usecase.AddFavouriteUseCase
import ru.itis.liiceberg.explore_api.domain.usecase.GetPlantByIdUseCase
import ru.itis.liiceberg.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class PlantsDetailsViewModel @Inject constructor(
    private val getPlantByIdUseCase: GetPlantByIdUseCase,
    private val addFavouriteUseCase: AddFavouriteUseCase,
) : BaseViewModel<PlantsDetailsState, PlantsDetailsEvent, PlantsDetailsAction>(PlantsDetailsState(null)) {

    fun addFavourite(plantId: String) {
        viewModelScope.launch {
            runCatching {
                addFavouriteUseCase.invoke(plantId)
            }.onSuccess {

            }.onFailure {

            }
        }
    }

    fun loadInfo(id: String) {
        viewModelScope.launch {
            runCatching {
                getPlantByIdUseCase.invoke(id)
            }.onSuccess {
                viewState = viewState.copy(
                    plantModel = it
                )
            }
        }
    }

}