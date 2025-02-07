package ru.itis.liiceberg.auth_impl.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.itis.liiceberg.auth_api.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(username: String, email: String, password: String) {
        withContext(dispatcher) {
            authRepository.register(username = username, email = email, password = password)
        }
    }

}