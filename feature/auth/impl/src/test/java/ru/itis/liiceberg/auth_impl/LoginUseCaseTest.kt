package ru.itis.liiceberg.auth_impl

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import ru.itis.liiceberg.auth_api.domain.repository.AuthRepository
import ru.itis.liiceberg.auth_impl.domain.usecase.LoginUseCase
import ru.itis.liiceberg.common.exceptions.AppException

@ExperimentalCoroutinesApi
class LoginUseCaseTest {

    private lateinit var loginUseCase: LoginUseCase
    private lateinit var authRepository: AuthRepository
    private val testDispatcher = Dispatchers.Unconfined

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        authRepository = mockk()

        loginUseCase = LoginUseCase(authRepository, testDispatcher)
    }

    @Test
    fun `invoke should call login with correct email and password`() = runTest {
        val email = "example@gmail.com"
        val password = "password"
        coEvery { authRepository.login(email, password) } returns Unit

        loginUseCase.invoke(email, password)

        coVerify(exactly = 1) { authRepository.login(email, password) }
    }


    @Test
    fun `invoke should call login with incorrect email or password`() = runTest {
        val email = "example@gmail.com"
        val password = "password"
        val expectedException = AppException.InvalidCredentials("")

        coEvery { authRepository.login(email, password) } throws expectedException

        assertThrows(AppException.InvalidCredentials::class.java) {
            runBlocking { loginUseCase.invoke(email, password) }
        }
    }

}