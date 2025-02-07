package ru.itis.liiceberg.myplants_impl.screens

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.liiceberg.myplants_api.domain.usecase.GetMyPlantsUseCase
import ru.itis.liiceberg.myplants_api.domain.usecase.RemoveFavouriteUseCase
import ru.itis.liiceberg.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MyPlantsViewModel @Inject constructor(
    private val getMyPlantsUseCase: GetMyPlantsUseCase,
    private val removeFavouriteUseCase: RemoveFavouriteUseCase,
) : BaseViewModel<MyPlantsState, MyPlantsEvent, MyPlantsAction>(
    MyPlantsState()
) {

    override fun init() {
        getPlants()
    }

    override fun obtainEvent(event: MyPlantsEvent) {
        when (event) {
            is MyPlantsEvent.RemovePlant -> { removeMyPlant(event.id) }
        }
    }

    private fun getPlants() {
        viewModelScope.launch {
            runCatching {
                getMyPlantsUseCase.invoke()
            }.onSuccess {
                viewState = viewState.copy(myPlants = it)
            }.onFailure { ex ->
                showError(ex.message)
            }
        }
    }

    private fun removeMyPlant(id: String) {
        viewModelScope.launch {
            runCatching {
                removeFavouriteUseCase.invoke(id)
            }.onSuccess {
                val newList = viewState.myPlants.filter { it.id != id }.toList()
                viewState = viewState.copy(myPlants = newList)
            }.onFailure { ex ->
                showError(ex.message)
            }
        }
    }
}