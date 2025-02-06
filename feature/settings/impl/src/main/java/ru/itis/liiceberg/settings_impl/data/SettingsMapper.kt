package ru.itis.liiceberg.settings_impl.data

import ru.itis.liiceberg.data.db.model.User
import ru.itis.liiceberg.settings_api.domain.model.UserModel
import javax.inject.Inject

class SettingsMapper @Inject constructor() {

    fun mapUserToUserModel(user: User?): UserModel {
        return UserModel(user?.username ?: "", user?.email ?: "")
    }

}