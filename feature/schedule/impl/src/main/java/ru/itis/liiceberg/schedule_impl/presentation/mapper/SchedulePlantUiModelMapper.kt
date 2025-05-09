package ru.itis.liiceberg.schedule_impl.presentation.mapper

import ru.itis.liiceberg.common.model.TimeValues
import ru.itis.liiceberg.common.resources.ResourceManager
import ru.itis.liiceberg.common.util.getValueInString
import ru.itis.liiceberg.schedule_api.domain.model.SchedulePlant
import ru.itis.liiceberg.schedule_api.presentation.ScheduleItem
import ru.itis.liiceberg.schedule_api.presentation.SchedulePlantUiModel
import ru.itis.liiceberg.ui.R
import javax.inject.Inject

class SchedulePlantUiModelMapper @Inject constructor(
    private val resourceManager: ResourceManager,
) {
    fun mapSchedulePlantToSchedulePlantUiModel(schedulePlant: SchedulePlant): SchedulePlantUiModel {

        return with(schedulePlant) {
            val wateringScheduleItem = ScheduleItem(
                wateringSchedule,
                getScheduleText(wateringSchedule),
                wateringInfo
            )
            val fertilizerScheduleItem = ScheduleItem(
                fertilizerSchedule,
                getScheduleText(fertilizerSchedule),
                fertilizerInfo
            )
            SchedulePlantUiModel(
                id,
                plantName,
                scientificName,
                image,
                wateringScheduleItem,
                fertilizerScheduleItem,
            )
        }
    }

    fun getScheduleText(period: TimeValues?) : String {
        val noScheduleText = resourceManager.getString(R.string.no_schedule)
        return period?.getValueInString(resourceManager) ?: noScheduleText
    }
}