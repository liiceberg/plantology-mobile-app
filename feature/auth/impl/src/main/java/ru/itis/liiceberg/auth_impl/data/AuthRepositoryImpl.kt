package ru.itis.liiceberg.auth_impl.data

import ru.itis.liiceberg.auth_api.domain.repository.AuthRepository
import ru.itis.liiceberg.data.db.dao.UserFirebaseDao
import ru.itis.liiceberg.data.storage.UserDataStore
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val userFirebaseDao: UserFirebaseDao,
    private val userDataStore: UserDataStore,
) : AuthRepository {

    override suspend fun register(username: String, email: String, password: String) {
        userFirebaseDao.createUserWithEmailAndPassword(
            username = username,
            email = email,
            password = password
        )
    }

    override suspend fun login(email: String, password: String){
        userFirebaseDao.signInUser(email, password)
        val id = userFirebaseDao.getCurrentUserId()
        id?.let { userDataStore.saveUserId(id) }
    }
}