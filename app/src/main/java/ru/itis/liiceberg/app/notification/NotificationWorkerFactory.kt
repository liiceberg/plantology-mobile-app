package ru.itis.liiceberg.app.notification

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import ru.itis.liiceberg.common.notification.NotificationManagerWrapper
import ru.itis.liiceberg.data.db.dao.FavouritesFirebaseDao
import ru.itis.liiceberg.data.db.dao.PlantFirebaseDao
import ru.itis.liiceberg.data.db.dao.TasksFirebaseDao
import ru.itis.liiceberg.data.reminder.NotificationWorker
import ru.itis.liiceberg.data.storage.UserDataStore
import javax.inject.Inject

class NotificationWorkerFactory @Inject constructor(
    private val notificationManagerWrapper: NotificationManagerWrapper,
    private val userDataStore: UserDataStore,
    private val favouritesDao: FavouritesFirebaseDao,
    private val tasksDao: TasksFirebaseDao,
    private val plantDao: PlantFirebaseDao,
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        return NotificationWorker(
            appContext,
            workerParameters,
            notificationManagerWrapper,
            userDataStore,
            favouritesDao,
            tasksDao,
            plantDao
        )
    }
}