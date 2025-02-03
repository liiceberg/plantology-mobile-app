package ru.itis.liiceberg.explore_impl.screens.explore

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.liiceberg.data.db.model.FloraCategory
import ru.itis.liiceberg.explore_api.domain.ExploreInteractor
import ru.itis.liiceberg.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val exploreInteractor: ExploreInteractor,
) : BaseViewModel<ExploreState, ExploreEvent, ExploreAction>(ExploreState()) {

    init {
        getPlants(FloraCategory.PLANT)
    }

    private fun getPlants(category: FloraCategory) {
        viewModelScope.launch {
            viewState = viewState.copy(
                items = exploreInteractor.getPlantsByCategory(category.stringValue())
            )
        }
    }

}