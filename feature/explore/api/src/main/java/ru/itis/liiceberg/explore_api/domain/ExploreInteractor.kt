package ru.itis.liiceberg.explore_api.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.itis.liiceberg.explore_api.domain.model.ExplorePlantModel
import ru.itis.liiceberg.explore_api.domain.model.PlantModel
import javax.inject.Inject

class ExploreInteractor @Inject constructor(
    private val exploreRepository: ExploreRepository,
    private val dispatcher: CoroutineDispatcher,
) {
    suspend fun getPlantsByCategory(category: String) : List<ExplorePlantModel> {
        return withContext(dispatcher) {
            exploreRepository.getPlantsByCategory(category)
        }
    }

    suspend fun getPlantsByNameAndCategory(category: String) : List<ExplorePlantModel> {
        return withContext(dispatcher) {
            exploreRepository.getPlantsByNameAndCategory()
        }
    }

    suspend fun getPlant(id: String) : PlantModel {
        return withContext(dispatcher) {
            exploreRepository.getPlant(id)
        }
    }
}