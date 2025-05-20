package ru.itis.liiceberg.settings_impl.data

import ru.itis.liiceberg.data.db.dao.UserFirebaseDao
import ru.itis.liiceberg.data.reminder.ReminderScheduler
import ru.itis.liiceberg.data.storage.UserDataStore
import ru.itis.liiceberg.settings_api.domain.model.UserModel
import ru.itis.liiceberg.settings_api.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val userDataStore: UserDataStore,
    private val userFirebaseDao: UserFirebaseDao,
    private val settingsMapper: SettingsMapper,
    private val reminderScheduler: ReminderScheduler,
) : SettingsRepository {

    override suspend fun getUserInfo(): UserModel {
        return settingsMapper.mapUserToUserModel(
            userFirebaseDao.getCurrentUser()
        )
    }

    override suspend fun verifyCurrentPassword(password: String) : Boolean {
        return userFirebaseDao.verifyCurrentPassword(password)
    }

    override suspend fun updatePassword(newPassword: String) {
        userFirebaseDao.updatePassword(newPassword)
    }

    override suspend fun logOut() {
        userDataStore.clearUserId()
        userFirebaseDao.signOut()
        reminderScheduler.cancel()
    }

}