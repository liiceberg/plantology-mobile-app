package ru.itis.liiceberg.explore_impl.data

import ru.itis.liiceberg.data.db.dao.PlantFirebaseDao
import ru.itis.liiceberg.data.db.model.FloraCategory
import ru.itis.liiceberg.explore_api.domain.ExploreRepository
import ru.itis.liiceberg.explore_api.domain.model.ExplorePlantModel
import ru.itis.liiceberg.explore_api.domain.model.PlantModel
import javax.inject.Inject

class ExploreRepositoryImpl @Inject constructor(
    private val plantFirebaseDao: PlantFirebaseDao,
    private val exploreMapper: ExploreMapper,
) : ExploreRepository {

    override suspend fun getPlantsByCategory(category: String): List<ExplorePlantModel> {
        return plantFirebaseDao.getPlantsByCategory(FloraCategory.fromString(category))
            .map { exploreMapper.mapPlantToExplorePlant(it) }
    }

    override suspend fun getPlantsByNameAndCategory(): List<ExplorePlantModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getPlant(id: String): PlantModel {
        return exploreMapper.mapFirebasePlantToPlantModel(
            plantFirebaseDao.getPlantById(id)
        )
    }
}
