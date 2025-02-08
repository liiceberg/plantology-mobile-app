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
import ru.itis.liiceberg.settings_impl.domain.usecase.ChangePasswordUseCase

@ExperimentalCoroutinesApi
class ChangePasswordUseCaseTest {

    private lateinit var changePasswordUseCase: ChangePasswordUseCase

    private lateinit var settingsRepository: SettingsRepository
    private val testDispatcher = Dispatchers.Unconfined

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        settingsRepository = mockk()

        changePasswordUseCase = ChangePasswordUseCase(settingsRepository, testDispatcher)
    }

    @Test
    fun `invoke should call updatePassword with correct newPassword`() = runTest {

        val newPassword = "newPassword"
        coEvery { settingsRepository.updatePassword(newPassword) } returns Unit

        changePasswordUseCase.invoke(newPassword)

        coVerify(exactly = 1) { settingsRepository.updatePassword(newPassword) }
    }

}