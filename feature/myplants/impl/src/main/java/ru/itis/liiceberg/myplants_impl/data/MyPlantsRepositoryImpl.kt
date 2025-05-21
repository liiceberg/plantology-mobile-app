package ru.itis.liiceberg.myplants_impl.data

import ru.itis.liiceberg.data.db.dao.FavouritesFirebaseDao
import ru.itis.liiceberg.data.db.dao.PlantFirebaseDao
import ru.itis.liiceberg.data.db.dao.TasksFirebaseDao
import ru.itis.liiceberg.data.storage.UserDataStore
import ru.itis.liiceberg.myplants_api.domain.repository.MyPlantsRepository
import ru.itis.liiceberg.myplants_api.domain.model.MyPlant
import javax.inject.Inject


class MyPlantsRepositoryImpl @Inject constructor(
    private val favouritesFirebaseDao: FavouritesFirebaseDao,
    private val tasksDao: TasksFirebaseDao,
    private val plantFirebaseDao: PlantFirebaseDao,
    private val myPlantsMapper: MyPlantsMapper,
    private val userDataStore: UserDataStore,
) : MyPlantsRepository {

    override suspend fun getAll(): List<MyPlant> {
        val userId = userDataStore.getUserId() ?: return emptyList()
        return favouritesFirebaseDao.getFavorites(userId).map {
            myPlantsMapper.mapPlantAndFavouritePlantToMyPlant(
                plantFirebaseDao.getPlantById(it.plantId), it
            )
        }.toList()
    }

    override suspend fun removeMyPlant(id: String) {
        favouritesFirebaseDao.removeFromFavorites(id)
        tasksDao.deleteTasksByFavourite(id)
    }
}