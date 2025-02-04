package ru.itis.liiceberg.explore_impl.screens.details

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.liiceberg.explore_api.domain.usecase.GetPlantByIdUseCase
import ru.itis.liiceberg.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class PlantsDetailsViewModel @Inject constructor(
    private val getPlantByIdUseCase: GetPlantByIdUseCase,
) : BaseViewModel<PlantsDetailsState, PlantsDetailsEvent, PlantsDetailsAction>(PlantsDetailsState(null)) {

    fun loadInfo(id: String) {
        viewModelScope.launch {
            viewState = viewState.copy(
                plantModel = getPlantByIdUseCase.invoke(id)
            )
        }
    }

}