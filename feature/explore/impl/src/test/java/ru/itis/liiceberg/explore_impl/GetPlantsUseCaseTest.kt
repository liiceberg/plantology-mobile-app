package ru.itis.liiceberg.explore_impl

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.itis.liiceberg.explore_api.domain.model.ExplorePlantModel
import ru.itis.liiceberg.explore_api.domain.repository.ExploreRepository
import ru.itis.liiceberg.explore_impl.domain.usecase.GetPlantsUseCase

@ExperimentalCoroutinesApi
class GetPlantsUseCaseTest {

    private lateinit var getPlantsUseCase: GetPlantsUseCase
    private lateinit var exploreRepository: ExploreRepository

    private val testDispatcher = Dispatchers.IO

    @Before
    fun setUp() {

        Dispatchers.setMain(testDispatcher)

        exploreRepository = mockk()

        getPlantsUseCase = GetPlantsUseCase(exploreRepository, testDispatcher)
    }

    @Test
    fun `invoke should return list of plants`() = runTest {

        val expectedPlants = listOf(
            ExplorePlantModel(
                id = "QAWSEDRFTGYHYHUJIKI",
                name = "Rose",
                "Scientific Rose",
                "https://flowers.ru/rose"
            ),
            ExplorePlantModel(
                id = "DVLDBPOTKBIBTBNJRKT",
                name = "Tulip",
                "Scientific Tulip",
                "https://flowers.ru/tulip"
            )
        )

        coEvery { exploreRepository.getAllPlants() } returns expectedPlants

        val result = getPlantsUseCase.invoke()

        assertEquals(expectedPlants, result)
    }

}