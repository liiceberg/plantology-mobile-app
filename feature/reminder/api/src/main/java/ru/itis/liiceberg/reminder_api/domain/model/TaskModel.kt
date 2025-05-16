package ru.itis.liiceberg.reminder_api.domain.model

import ru.itis.liiceberg.common.model.TaskType
import ru.itis.liiceberg.common.model.TimeUnit
import ru.itis.liiceberg.common.model.TimeValues
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
        val nextCaringDate = getNextCaringDate()
        val otherNextCaringDate = other.getNextCaringDate()

        return when {
            nextCaringDate == otherNextCaringDate -> 0
            nextCaringDate > otherNextCaringDate -> 1
            else -> -1
        }
    }

    fun getNextCaringDate(): LocalDate {
        return when (period.periodUnit) {
            TimeUnit.DAYS -> lastCaringDate.plusDays(period.periodValue.toLong())
            TimeUnit.WEEKS -> lastCaringDate.plusWeeks(period.periodValue.toLong())
            TimeUnit.MONTHS -> lastCaringDate.plusMonths(period.periodValue.toLong())
        }
    }
}