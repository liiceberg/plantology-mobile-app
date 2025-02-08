package ru.itis.liiceberg.auth_impl

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.itis.liiceberg.auth_api.domain.repository.AuthRepository
import ru.itis.liiceberg.auth_impl.domain.usecase.RegisterUseCase
import ru.itis.liiceberg.common.exceptions.AppException

@ExperimentalCoroutinesApi
class RegisterUseCaseTest {

    private lateinit var registerUseCase: RegisterUseCase
    private lateinit var authRepository: AuthRepository
    private val testDispatcher = Dispatchers.Unconfined

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        authRepository = mockk()

        registerUseCase = RegisterUseCase(authRepository, testDispatcher)
    }

    @Test
    fun `invoke should call register with correct username, email and password`() = runTest {
        val username = "username"
        val email = "example@gmail.com"
        val password = "password"
        coEvery { authRepository.register(username, email, password) } returns Unit

        registerUseCase.invoke(username, email, password)

        coVerify(exactly = 1) { authRepository.register(username, email, password) }
    }


    @Test
    fun `invoke should call register with registered email`() = runTest {
        val username = "username"
        val email = "example@gmail.com"
        val password = "password"
        val expectedException = AppException.SuchEmailAlreadyRegistered("")

        coEvery { authRepository.register(username, email, password) } throws expectedException

        try {
            registerUseCase.invoke(username, email, password)
            throw AssertionError("Expected SuchEmailAlreadyRegistered exception, but no exception was thrown")
        } catch (e: AppException.SuchEmailAlreadyRegistered) {
            assertEquals(expectedException.message, e.message)
        }
    }

}