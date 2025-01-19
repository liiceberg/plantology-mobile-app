package ru.itis.liiceberg.auth_impl.data

import ru.itis.liiceberg.auth_api.domain.AuthRepository
import ru.itis.liiceberg.data.db.dao.UserFirebaseDao
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val userFirebaseDao: UserFirebaseDao
) : AuthRepository {

    override suspend fun register(username: String, email: String, password: String) {
        userFirebaseDao.createUserWithEmailAndPassword(email, password)
    }

    override suspend fun login(email: String, password: String) {
        userFirebaseDao.signInUser(email, password)
    }
}