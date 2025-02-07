package ru.itis.liiceberg.explore_api.domain.repository

import ru.itis.liiceberg.explore_api.domain.model.ExplorePlantModel
import ru.itis.liiceberg.explore_api.domain.model.PlantModel

interface ExploreRepository {
    suspend fun getAllPlants() : List<ExplorePlantModel>
    suspend fun getPlant(id: String) : PlantModel
    suspend fun addFavouritePlant(id: String)
}