package ru.itis.liiceberg.reminder_impl.data

import ru.itis.liiceberg.common.model.TaskType
import ru.itis.liiceberg.data.db.dao.FavouritesFirebaseDao
import ru.itis.liiceberg.data.db.dao.PlantFirebaseDao
import ru.itis.liiceberg.data.db.dao.TasksFirebaseDao
import ru.itis.liiceberg.data.storage.UserDataStore
import ru.itis.liiceberg.reminder_api.domain.model.TaskModel
import ru.itis.liiceberg.reminder_api.domain.repository.ReminderRepository
import javax.inject.Inject

class ReminderRepositoryImpl @Inject constructor(
    private val favouritesFirebaseDao: FavouritesFirebaseDao,
    private val plantsFirebaseDao: PlantFirebaseDao,
    private val tasksFirebaseDao: TasksFirebaseDao,
    private val userDataStore: UserDataStore,
    private val mapper: ReminderMapper,
) : ReminderRepository {

    override suspend fun getTasks(): List<TaskModel> {
        val userId = userDataStore.getUserId() ?: return emptyList()
        val favs = favouritesFirebaseDao.getFavorites(userId)
        val tasksList = mutableListOf<TaskModel>()
        for (fav in favs) {
            fav.id?.let {
                val tasks = tasksFirebaseDao.getTasksByFavourite(it)
                if (tasks.isNotEmpty()) {
                    val plant =  plantsFirebaseDao.getPlantById(fav.plantId)
                    tasks.forEach { task ->
                        val period = when (task.type) {
                            TaskType.WATER -> fav.wateringPeriod
                            TaskType.FERTILIZER -> fav.fertilizerPeriod
                            TaskType.UNKNOWN -> null
                        }
                        tasksList.add(
                            mapper.mapTaskWithPlantToTaskModel(
                                task,
                                plant,
                                period
                            )
                        )
                    }
                }
            }

        }
        return tasksList
    }

    override suspend fun completeTask(id: String) {
        tasksFirebaseDao.completeTask(id)
    }
}