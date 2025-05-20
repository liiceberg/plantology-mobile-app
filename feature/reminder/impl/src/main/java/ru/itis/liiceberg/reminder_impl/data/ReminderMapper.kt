package ru.itis.liiceberg.reminder_impl.data

import ru.itis.liiceberg.common.model.TimeUnit
import ru.itis.liiceberg.common.model.TimeValues
import ru.itis.liiceberg.common.util.toLocalDate
import ru.itis.liiceberg.data.db.model.Plant
import ru.itis.liiceberg.data.db.model.Task
import ru.itis.liiceberg.reminder_api.domain.model.TaskModel
import java.time.LocalDate
import javax.inject.Inject

class ReminderMapper @Inject constructor() {

    fun mapTaskWithPlantToTaskModel(task: Task, plant: Plant?, period: TimeValues?): TaskModel {
        return TaskModel(
            id = task.id ?: "",
            type = task.type,
            plantName = plant?.name ?: "",
            imageUrl = plant?.image?.firstOrNull() ?: "",
            lastCaringDate = task.lastCaringDate?.toLocalDate() ?: LocalDate.now(),
            period = period ?: TimeValues(0, TimeUnit.DAYS)
        )
    }
}