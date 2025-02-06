package ru.itis.liiceberg.explore_api.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.itis.liiceberg.explore_api.domain.repository.ExploreRepository
import javax.inject.Inject

class AddFavouriteUseCase @Inject constructor(
    private val exploreRepository: ExploreRepository,
    private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(plantId: String) {
        withContext(dispatcher) {
            exploreRepository.addFavouritePlant(plantId)
        }
    }
}