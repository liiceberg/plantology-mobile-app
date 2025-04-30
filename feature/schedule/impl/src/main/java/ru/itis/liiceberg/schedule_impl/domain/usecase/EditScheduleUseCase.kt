package ru.itis.liiceberg.schedule_impl.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.itis.liiceberg.schedule_api.domain.repository.EditScheduleRepository
import javax.inject.Inject

class EditScheduleUseCase @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val repository: EditScheduleRepository,
) {
    suspend operator fun invoke() {
        withContext(dispatcher) {
            repository.saveSchedule()
        }
    }
}