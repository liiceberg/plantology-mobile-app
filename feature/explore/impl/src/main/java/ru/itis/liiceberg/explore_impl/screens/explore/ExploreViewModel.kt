package ru.itis.liiceberg.explore_impl.screens.explore

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.liiceberg.data.db.model.FloraCategory
import ru.itis.liiceberg.explore_api.domain.usecase.GetPlantsUseCase
import ru.itis.liiceberg.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val getPlantsUseCase: GetPlantsUseCase,
) : BaseViewModel<ExploreState, ExploreEvent, ExploreAction>(ExploreState()) {

    init {
        getPlants(FloraCategory.PLANT)
    }

    private fun getPlants(category: FloraCategory) {
        viewModelScope.launch {
            viewState = viewState.copy(
                items = getPlantsUseCase.invoke(category.stringValue())
            )
        }
    }

}