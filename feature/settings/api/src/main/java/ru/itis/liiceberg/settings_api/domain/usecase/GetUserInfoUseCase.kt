package ru.itis.liiceberg.settings_api.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.itis.liiceberg.settings_api.domain.model.UserModel
import ru.itis.liiceberg.settings_api.domain.repository.SettingsRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke() : UserModel {
        return withContext(dispatcher) {
            settingsRepository.getUserInfo()
        }
    }

}