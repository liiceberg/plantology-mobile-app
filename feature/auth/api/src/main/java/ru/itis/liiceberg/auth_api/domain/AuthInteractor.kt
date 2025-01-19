package ru.itis.liiceberg.auth_api.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val authRepository: AuthRepository,
    private val dispatcher: CoroutineDispatcher,
) {
    suspend fun register(username: String, email: String, password: String) {
        withContext(dispatcher) {
            authRepository.register(username, email, password)
        }
    }

    suspend fun login(email: String, password: String) {
        withContext(dispatcher) {
            authRepository.login(email, password)
        }
    }
}