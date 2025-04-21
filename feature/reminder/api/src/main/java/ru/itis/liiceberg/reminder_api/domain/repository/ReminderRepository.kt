package ru.itis.liiceberg.reminder_api.domain.repository

import ru.itis.liiceberg.reminder_api.domain.model.TaskModel

interface ReminderRepository {
    suspend fun getTasks() : List<TaskModel>
}