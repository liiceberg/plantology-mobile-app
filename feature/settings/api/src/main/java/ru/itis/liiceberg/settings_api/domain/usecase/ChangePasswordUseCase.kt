package ru.itis.liiceberg.settings_api.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.itis.liiceberg.settings_api.domain.repository.SettingsRepository
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke() {
        withContext(dispatcher) {
            settingsRepository
        }
    }

}