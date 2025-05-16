package ru.itis.liiceberg.reminder_impl.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.itis.liiceberg.reminder_api.domain.model.TaskModel
import ru.itis.liiceberg.reminder_api.domain.repository.ReminderRepository
import java.time.LocalDate
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val reminderRepository: ReminderRepository,
) {
    suspend operator fun invoke() : List<TaskModel> {
        return withContext(dispatcher) {
            reminderRepository.getTasks().filter { it.lastCaringDate != LocalDate.now() }
        }
    }
}