package ru.itis.liiceberg.myplants_api.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.itis.liiceberg.myplants_api.domain.MyPlantsRepository
import ru.itis.liiceberg.myplants_api.domain.model.MyPlant
import javax.inject.Inject

class GetMyPlantsUseCase @Inject constructor(
    private val myPlantsRepository: MyPlantsRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke() : List<MyPlant> {
        return withContext(dispatcher) {
            myPlantsRepository.getAll()
        }
    }

}