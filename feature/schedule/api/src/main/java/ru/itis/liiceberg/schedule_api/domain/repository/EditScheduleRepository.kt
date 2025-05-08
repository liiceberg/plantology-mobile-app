package ru.itis.liiceberg.schedule_api.domain.repository

import ru.itis.liiceberg.common.model.TimeValues
import ru.itis.liiceberg.schedule_api.domain.model.SchedulePlant

interface EditScheduleRepository {
    suspend fun saveSchedule(
        favId: String,
        wateringPeriod: TimeValues?,
        fertilizerPeriod: TimeValues?,
    )

    suspend fun getPlantInfo(plantId: String): SchedulePlant
}