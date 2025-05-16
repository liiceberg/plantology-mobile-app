package ru.itis.liiceberg.reminder_api.presentation.model

import ru.itis.liiceberg.common.model.TaskType

data class TaskUiModel(
    val id: String,
    val type: TaskType,
    val plantName: String,
    val imageUrl: String,
    val dateText: String,
    val completed: Boolean,
    val time: Time,
)

enum class Time {
    PAST, PRESENT, FUTURE
}