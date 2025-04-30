package ru.itis.liiceberg.schedule_api.presentation

import ru.itis.liiceberg.common.model.TimeValues

data class SchedulePlantUiModel(
    val id: String,
    val plantName: String,
    val scientificName: String,
    val image: String,
    val wateringSchedule: ScheduleItem,
    val fertilizerSchedule: ScheduleItem,
) {
    companion object {
        fun empty(): SchedulePlantUiModel {
            val emptySchedule = ScheduleItem(null, "", "")
            return SchedulePlantUiModel("", "", "", "", emptySchedule, emptySchedule)
        }
    }
}

data class ScheduleItem(
    val value: TimeValues?,
    val stringValue: String,
    val description: String,
)
