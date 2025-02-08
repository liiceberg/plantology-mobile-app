package ru.itis.liiceberg.explore_impl.data

import ru.itis.liiceberg.data.db.dao.FavouritesFirebaseDao
import ru.itis.liiceberg.data.db.dao.PlantFirebaseDao
import ru.itis.liiceberg.data.storage.UserDataStore
import ru.itis.liiceberg.explore_api.domain.model.ExplorePlantModel
import ru.itis.liiceberg.explore_api.domain.model.PlantModel
import ru.itis.liiceberg.explore_api.domain.repository.ExploreRepository
import javax.inject.Inject

class ExploreRepositoryImpl @Inject constructor(
    private val plantFirebaseDao: PlantFirebaseDao,
    private val userDataStore: UserDataStore,
    private val favouritesFirebaseDao: FavouritesFirebaseDao,
    private val exploreMapper: ExploreMapper,
) : ExploreRepository {

    override suspend fun getAllPlants(): List<ExplorePlantModel> {
        return plantFirebaseDao.getAllPlants()
            .map { exploreMapper.mapPlantToExplorePlant(it) }
    }

    override suspend fun getPlant(id: String): PlantModel {
        val saved = userDataStore.getUserId()
            ?.let { userId ->
                favouritesFirebaseDao.getFavorites(userId)
            }
            ?.map { fav -> fav.plantId }
            ?.contains(id)

        return exploreMapper.mapFirebasePlantToPlantModel(
            plantFirebaseDao.getPlantById(id), saved
        )
    }

    override suspend fun addFavouritePlant(id: String) {
        val userId = userDataStore.getUserId() ?: return
        favouritesFirebaseDao.addToFavorites(userId, id)
    }
}
