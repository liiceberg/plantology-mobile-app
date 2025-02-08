package ru.itis.liiceberg.explore_impl.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.itis.liiceberg.explore_api.domain.model.ExplorePlantModel
import ru.itis.liiceberg.explore_api.domain.repository.ExploreRepository
import javax.inject.Inject

class GetPlantsUseCase @Inject constructor(
    private val exploreRepository: ExploreRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(): List<ExplorePlantModel> {
        return withContext(dispatcher) {
            exploreRepository.getAllPlants()
        }
    }

}