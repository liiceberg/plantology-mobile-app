package ru.itis.liiceberg.schedule_impl.data

import ru.itis.liiceberg.common.model.TaskType
import ru.itis.liiceberg.common.model.TimeValues
import ru.itis.liiceberg.data.db.dao.FavouritesFirebaseDao
import ru.itis.liiceberg.data.db.dao.PlantFirebaseDao
import ru.itis.liiceberg.data.db.dao.TasksFirebaseDao
import ru.itis.liiceberg.data.storage.UserDataStore
import ru.itis.liiceberg.schedule_api.domain.model.SchedulePlant
import ru.itis.liiceberg.schedule_api.domain.repository.EditScheduleRepository
import javax.inject.Inject

class EditScheduleRepositoryImpl @Inject constructor(
    private val plantFirebaseDao: PlantFirebaseDao,
    private val favouritesFirebaseDao: FavouritesFirebaseDao,
    private val tasksFirebaseDao: TasksFirebaseDao,
    private val userDataStore: UserDataStore,
    private val mapper: SchedulePlantMapper,
) : EditScheduleRepository {

    override suspend fun saveSchedule(
        favId: String,
        wateringPeriod: TimeValues?,
        fertilizerPeriod: TimeValues?,
    ) {
        favouritesFirebaseDao.updateFavouriteInfo(favId, wateringPeriod, fertilizerPeriod)

        if (wateringPeriod != null) {
            tasksFirebaseDao.addTask(favId, TaskType.WATER)
        } else {
            tasksFirebaseDao.deleteTask(favId, TaskType.WATER)
        }

        if (fertilizerPeriod != null) {
            tasksFirebaseDao.addTask(favId, TaskType.FERTILIZER)
        } else {
            tasksFirebaseDao.deleteTask(favId, TaskType.FERTILIZER)
        }

    }

    override suspend fun getPlantInfo(plantId: String): SchedulePlant {
        val plant = plantFirebaseDao.getPlantById(plantId)
        val userId = userDataStore.getUserId() ?: throw NullPointerException()
        val fav = favouritesFirebaseDao.getFavouriteInfo(userId = userId, plantId = plantId)
        return mapper.mapPlantAndFavouriteToSchedulePlant(plant, fav)
    }
}