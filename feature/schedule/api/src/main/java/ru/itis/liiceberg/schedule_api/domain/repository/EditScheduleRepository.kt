package ru.itis.liiceberg.schedule_api.domain.repository

import ru.itis.liiceberg.schedule_api.domain.model.SchedulePlant

interface EditScheduleRepository {
    suspend fun saveSchedule()
    suspend fun getPlantInfo(plantId: String) : SchedulePlant
}