package ru.itis.liiceberg.reminder_impl.presentation.mapper

import ru.itis.liiceberg.common.resources.ResourceManager
import ru.itis.liiceberg.reminder_api.domain.model.TaskModel
import ru.itis.liiceberg.reminder_api.presentation.model.TaskUiModel
import ru.itis.liiceberg.reminder_api.presentation.model.Time
import ru.itis.liiceberg.reminder_impl.R
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class TaskModelMapper @Inject constructor(
    private val resourceManager: ResourceManager,
) {
    fun mapTaskModelToTaskUiModel(task: TaskModel): TaskUiModel {
        with(task) {

            val today = LocalDate.now()
            val nextCaringDate = getNextCaringDate()
            val daysDifference = ChronoUnit.DAYS.between(nextCaringDate, today)
            val dateText: String
            val completed: Boolean
            if (lastCaringDate == today) {
                dateText = resourceManager.getString(R.string.today_care)
                completed = true
            } else {
                completed = false
                dateText = when {
                    daysDifference == 0L -> resourceManager.getString(R.string.today_care)
                    daysDifference > 0 -> resourceManager.getString(R.string.late_care, daysDifference)
                    else -> resourceManager.getString(R.string.future_care, -daysDifference)
                }
            }

            return TaskUiModel(
                type = type,
                plantName = plantName,
                imageUrl = imageUrl,
                dateText = dateText,
                completed = completed,
                time = Time.PRESENT,
            )
        }
    }

}