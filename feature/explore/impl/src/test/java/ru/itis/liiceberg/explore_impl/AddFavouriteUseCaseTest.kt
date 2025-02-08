package ru.itis.liiceberg.explore_impl

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import ru.itis.liiceberg.explore_api.domain.repository.ExploreRepository
import ru.itis.liiceberg.explore_impl.domain.usecase.AddFavouriteUseCase


class AddFavouriteUseCaseTest {
    private lateinit var addFavouriteUseCase: AddFavouriteUseCase
    private lateinit var exploreRepository: ExploreRepository

    private val testDispatcher = Dispatchers.IO

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {

        Dispatchers.setMain(testDispatcher)

        exploreRepository = mockk()

        addFavouriteUseCase = AddFavouriteUseCase(exploreRepository, testDispatcher)
    }

    @Test
    fun `invoke should call addFavouritePlant with correct plantId`() = runTest {

        val plantId = "JSBCJBSHFJBDFJVB"

        coEvery { exploreRepository.addFavouritePlant(plantId) } returns Unit

        addFavouriteUseCase.invoke(plantId)

        coVerify(exactly = 1) { exploreRepository.addFavouritePlant(plantId) }
    }

}