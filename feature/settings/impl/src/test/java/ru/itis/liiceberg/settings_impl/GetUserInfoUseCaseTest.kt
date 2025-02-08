package ru.itis.liiceberg.settings_impl

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
import ru.itis.liiceberg.settings_api.domain.model.UserModel
import ru.itis.liiceberg.settings_api.domain.repository.SettingsRepository
import ru.itis.liiceberg.settings_impl.domain.usecase.GetUserInfoUseCase

@ExperimentalCoroutinesApi
class GetUserInfoUseCaseTest {

    private lateinit var getUserInfoUseCase: GetUserInfoUseCase

    private lateinit var settingsRepository: SettingsRepository
    private val testDispatcher = Dispatchers.Unconfined

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        settingsRepository = mockk()

        getUserInfoUseCase = GetUserInfoUseCase(settingsRepository, testDispatcher)
    }

    @Test
    fun `invoke should return user info`() = runTest {
        val expectedUser = UserModel(username = "User", email = "example@mail.ru")
        coEvery { settingsRepository.getUserInfo() } returns expectedUser

        val result = getUserInfoUseCase.invoke()

        assertEquals(expectedUser, result)
        coVerify(exactly = 1) { settingsRepository.getUserInfo() }
    }

    @Test
    fun `invoke should return empty user info when repository returns null`() = runTest {
        val expectedUser = UserModel(username = "", email = "")
        coEvery { settingsRepository.getUserInfo() } returns expectedUser

        val result = getUserInfoUseCase()

        assertEquals(expectedUser, result)
        coVerify(exactly = 1) { settingsRepository.getUserInfo() }
    }
}