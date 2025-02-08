package ru.itis.liiceberg.explore_impl.presentation.screens.explore

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.itis.liiceberg.common.exceptions.ExceptionHandlerDelegate
import ru.itis.liiceberg.common.exceptions.runCatching
import ru.itis.liiceberg.explore_impl.domain.usecase.GetPlantsUseCase
import ru.itis.liiceberg.ui.base.BaseViewModel
import ru.itis.liiceberg.ui.model.LoadState
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val getPlantsUseCase: GetPlantsUseCase,
    private val exceptionHandler: ExceptionHandlerDelegate,
) : BaseViewModel<ExploreState, ExploreEvent, ExploreAction>(ExploreState()) {

    override fun init() {
        getPlants()
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
                viewState = viewState.copy(loadState = LoadState.Success)
                viewState = viewState.copy(
                    items = it
                )
            }.onFailure { ex ->
                ex.message?.let { onError(it) }
            }
        }
    }

}