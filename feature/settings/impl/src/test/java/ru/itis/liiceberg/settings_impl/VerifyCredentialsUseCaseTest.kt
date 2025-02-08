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
import ru.itis.liiceberg.settings_api.domain.repository.SettingsRepository
import ru.itis.liiceberg.settings_impl.domain.usecase.VerifyCredentialsUseCase

@ExperimentalCoroutinesApi
class VerifyCredentialsUseCaseTest {

    private lateinit var verifyCredentialsUseCase: VerifyCredentialsUseCase

    private lateinit var settingsRepository: SettingsRepository
    private val testDispatcher = Dispatchers.Unconfined

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        settingsRepository = mockk()

        verifyCredentialsUseCase = VerifyCredentialsUseCase(settingsRepository, testDispatcher)
    }

    @Test
    fun `invoke should return true when password is correct`() = runTest {

        val correctPassword = "correctPassword"
        coEvery { settingsRepository.verifyCurrentPassword(correctPassword) } returns true

        val result = verifyCredentialsUseCase(correctPassword)

        assertEquals(true, result)
        coVerify(exactly = 1) { settingsRepository.verifyCurrentPassword(correctPassword) }
    }

    @Test
    fun `invoke should return false when password is incorrect`() = runTest {

        val incorrectPassword = "incorrectPassword"
        coEvery { settingsRepository.verifyCurrentPassword(incorrectPassword) } returns false

        val result = verifyCredentialsUseCase(incorrectPassword)

        assertEquals(false, result)
        coVerify(exactly = 1) { settingsRepository.verifyCurrentPassword(incorrectPassword) }
    }
}