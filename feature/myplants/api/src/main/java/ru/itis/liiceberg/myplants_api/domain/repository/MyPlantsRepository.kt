package ru.itis.liiceberg.myplants_api.domain.repository

import ru.itis.liiceberg.myplants_api.domain.model.MyPlant

interface MyPlantsRepository {
    suspend fun getAll() : List<MyPlant>
    suspend fun removeMyPlant(id: String)
}
