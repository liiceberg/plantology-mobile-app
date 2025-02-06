package ru.itis.liiceberg.settings_api.domain.repository

import ru.itis.liiceberg.settings_api.domain.model.UserModel

interface SettingsRepository {
    suspend fun logOut()
    suspend fun getUserInfo() : UserModel
    suspend fun verifyCurrentPassword(password: String) : Boolean
    suspend fun updatePassword(newPassword: String)
}