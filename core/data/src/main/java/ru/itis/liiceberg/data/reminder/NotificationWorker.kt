package ru.itis.liiceberg.data.reminder

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.itis.liiceberg.common.model.TaskType
import ru.itis.liiceberg.common.notification.NotificationManagerWrapper
import ru.itis.liiceberg.common.util.getNextDate
import ru.itis.liiceberg.common.util.toLocalDate
import ru.itis.liiceberg.data.db.dao.FavouritesFirebaseDao
import ru.itis.liiceberg.data.db.dao.PlantFirebaseDao
import ru.itis.liiceberg.data.db.dao.TasksFirebaseDao
import ru.itis.liiceberg.data.storage.UserDataStore
import java.time.LocalDate

@HiltWorker
class NotificationWorker @AssistedInject constructor(
    @Assisted workerParams: WorkerParameters,
    @ApplicationContext private val appContext: Context,
    private val notificationManagerWrapper: NotificationManagerWrapper,
    private val userDataStore: UserDataStore,
    private val favouritesDao: FavouritesFirebaseDao,
    private val tasksDao: TasksFirebaseDao,
    private val plantDao: PlantFirebaseDao,
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        if (ActivityCompat.checkSelfPermission(
                appContext,
                Manifest.permission.POST_NOTIFICATIONS
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            val userId = userDataStore.getUserId() ?: return Result.failure()
            favouritesDao.getFavorites(userId).forEach { favPlant ->
                favPlant.id?.let {
                    tasksDao.getTasksByFavourite(it).forEach { task ->
                        task.lastCaringDate?.toLocalDate()?.let { previousDate ->

                            when (task.type) {
                                TaskType.WATER -> favPlant.wateringPeriod
                                else -> favPlant.fertilizerPeriod
                            }?.let {
                                val caringDate = previousDate.getNextDate(it)
                                if (caringDate.isAfter(LocalDate.now()).not()) {
                                    showNotification(task.type, favPlant.plantId)
                                }
                            }
                        }
                    }

                }
            }
        }
        return Result.success()
    }

    private suspend fun showNotification(taskType: TaskType, plantId: String) {
        val plant = plantDao.getPlantById(plantId)
        notificationManagerWrapper.notify(
            id = plant?.id.hashCode(),
            plantName = plant?.name ?: "plant",
            type = taskType,
        )
    }
}