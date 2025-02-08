package ru.itis.liiceberg.myplants_impl.presentation.screens

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.itis.liiceberg.common.exceptions.ExceptionHandlerDelegate
import ru.itis.liiceberg.common.exceptions.runCatching
import ru.itis.liiceberg.myplants_impl.domain.usecase.GetMyPlantsUseCase
import ru.itis.liiceberg.myplants_impl.domain.usecase.RemoveFavouriteUseCase
import ru.itis.liiceberg.ui.base.BaseViewModel
import ru.itis.liiceberg.ui.model.LoadState
import javax.inject.Inject

@HiltViewModel
class MyPlantsViewModel @Inject constructor(
    private val getMyPlantsUseCase: GetMyPlantsUseCase,
    private val removeFavouriteUseCase: RemoveFavouriteUseCase,
    private val exceptionHandler: ExceptionHandlerDelegate,
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
                getMyPlantsUseCase.invoke()
            }.onSuccess {
                viewState = viewState.copy(loadState = LoadState.Success)
                viewState = viewState.copy(myPlants = it)
            }.onFailure { ex ->
                ex.message?.let { onError(it) }
            }
        }
    }

    private fun removeMyPlant(id: String) {
        viewModelScope.launch {
            runCatching(exceptionHandler) {
                viewState = viewState.copy(loadState = LoadState.Loading)
                removeFavouriteUseCase.invoke(id)
            }.onSuccess {
                viewState = viewState.copy(loadState = LoadState.Success)
                val newList = viewState.myPlants.filter { it.id != id }.toList()
                viewState = viewState.copy(myPlants = newList)
            }.onFailure { ex ->
                ex.message?.let { onError(it) }
            }
        }
    }
}