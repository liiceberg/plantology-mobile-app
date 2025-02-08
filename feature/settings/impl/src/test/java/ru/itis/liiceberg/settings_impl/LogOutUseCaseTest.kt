package ru.itis.liiceberg.settings_impl

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import ru.itis.liiceberg.settings_api.domain.repository.SettingsRepository
import ru.itis.liiceberg.settings_impl.domain.usecase.LogOutUseCase

@ExperimentalCoroutinesApi
class LogOutUseCaseTest {

    private lateinit var logOutUseCase: LogOutUseCase
    private lateinit var settingsRepository: SettingsRepository
    private val testDispatcher = Dispatchers.Unconfined

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        settingsRepository = mockk()

        logOutUseCase = LogOutUseCase(settingsRepository, testDispatcher)
    }

    @Test
    fun `invoke should call logOut`() = runTest {

        coEvery { settingsRepository.logOut() } returns Unit

        logOutUseCase.invoke()

        coVerify(exactly = 1) { settingsRepository.logOut() }
    }
}