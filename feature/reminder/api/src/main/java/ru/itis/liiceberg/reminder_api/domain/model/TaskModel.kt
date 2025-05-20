package ru.itis.liiceberg.reminder_api.domain.model

import ru.itis.liiceberg.common.model.TaskType
import ru.itis.liiceberg.common.model.TimeValues
import ru.itis.liiceberg.common.util.getNextDate
import java.time.LocalDate

data class TaskModel(
    val id: String,
    val type: TaskType,
    val plantName: String,
    val imageUrl: String,
    val lastCaringDate: LocalDate,
    val period: TimeValues,
) : Comparable<TaskModel> {

    override fun compareTo(other: TaskModel): Int {
        val nextCaringDate = lastCaringDate.getNextDate(period)
        val otherNextCaringDate = other.lastCaringDate.getNextDate(other.period)

        return when {
            nextCaringDate == otherNextCaringDate -> 0
            nextCaringDate > otherNextCaringDate -> 1
            else -> -1
        }
    }

}