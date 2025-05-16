package ru.itis.liiceberg.reminder_impl.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.itis.liiceberg.reminder_api.domain.repository.ReminderRepository
import javax.inject.Inject

class CompleteTaskUseCase @Inject constructor(
    private val repository: ReminderRepository,
    private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(id: String) {
        withContext(dispatcher) {
            repository.completeTask(id)
        }
    }
}