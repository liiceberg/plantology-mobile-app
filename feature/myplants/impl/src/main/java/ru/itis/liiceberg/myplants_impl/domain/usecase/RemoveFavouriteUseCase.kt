package ru.itis.liiceberg.myplants_impl.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.itis.liiceberg.myplants_api.domain.repository.MyPlantsRepository
import javax.inject.Inject

class RemoveFavouriteUseCase @Inject constructor(
    private val myPlantsRepository: MyPlantsRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(id: String) {
        withContext(dispatcher) {
            myPlantsRepository.removeMyPlant(id)
        }
    }
}