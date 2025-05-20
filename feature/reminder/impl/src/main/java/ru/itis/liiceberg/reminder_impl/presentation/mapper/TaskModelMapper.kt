package ru.itis.liiceberg.reminder_impl.presentation.mapper

import ru.itis.liiceberg.common.resources.ResourceManager
import ru.itis.liiceberg.common.util.getNextDate
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
            val nextCaringDate = lastCaringDate.getNextDate(period)
            val daysDifference = ChronoUnit.DAYS.between(nextCaringDate, today)
            val dateText: String
            val time: Time
            when {
                daysDifference == 0L -> {
                    time = Time.PRESENT
                    dateText = resourceManager.getString(R.string.today_care)
                }
                daysDifference > 0 -> {
                    time = Time.PAST
                    dateText = resourceManager.getString(R.string.late_care, daysDifference)
                }
                else -> {
                    time = Time.FUTURE
                    dateText = resourceManager.getString(R.string.future_care, -daysDifference)
                }
            }
            return TaskUiModel(
                id = id,
                type = type,
                plantName = plantName,
                imageUrl = imageUrl,
                dateText = dateText,
                completed = false,
                time = time,
            )
        }
    }

}