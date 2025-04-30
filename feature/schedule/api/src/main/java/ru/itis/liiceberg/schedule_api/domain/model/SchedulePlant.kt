package ru.itis.liiceberg.schedule_api.domain.model

import ru.itis.liiceberg.common.model.TimeValues

data class SchedulePlant(
    val id: String,
    val plantName: String,
    val scientificName: String,
    val image: String,
    val wateringInfo: String,
    val fertilizerInfo: String,
    val wateringSchedule: TimeValues?,
    val fertilizerSchedule: TimeValues?,
)
