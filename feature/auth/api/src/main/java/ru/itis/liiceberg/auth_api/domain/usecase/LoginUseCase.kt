package ru.itis.liiceberg.auth_api.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.itis.liiceberg.auth_api.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(email: String, password: String) {
        withContext(dispatcher) {
            authRepository.login(email, password)
        }
    }

}