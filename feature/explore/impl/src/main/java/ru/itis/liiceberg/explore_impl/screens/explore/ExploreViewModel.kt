package ru.itis.liiceberg.explore_impl.screens.explore

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.liiceberg.common.exceptions.ExceptionHandlerDelegate
import ru.itis.liiceberg.common.exceptions.runCatching
import ru.itis.liiceberg.data.db.model.FloraCategory
import ru.itis.liiceberg.explore_api.domain.usecase.GetPlantsUseCase
import ru.itis.liiceberg.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val getPlantsUseCase: GetPlantsUseCase,
    private val exceptionHandler: ExceptionHandlerDelegate,
) : BaseViewModel<ExploreState, ExploreEvent, ExploreAction>(ExploreState()) {

    override fun init() {
        getPlants()
    }

    private fun getPlants() {
        viewModelScope.launch {
            runCatching(exceptionHandler) {
                getPlantsUseCase.invoke(FloraCategory.PLANT.stringValue())
            }.onSuccess {
                viewState = viewState.copy(
                    items = it
                )
            }.onFailure { ex ->
                showError(ex.message)
            }
        }
    }

}