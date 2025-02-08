package ru.itis.liiceberg.explore_impl.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.itis.liiceberg.explore_api.domain.repository.ExploreRepository
import ru.itis.liiceberg.explore_api.domain.model.PlantModel
import javax.inject.Inject

class GetPlantByIdUseCase @Inject constructor(
    private val exploreRepository: ExploreRepository,
    private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(id: String) : PlantModel {
        return withContext(dispatcher) {
            exploreRepository.getPlant(id)
        }
    }
}