package ru.itis.liiceberg.myplants_impl.presentation

import ru.itis.liiceberg.common.resources.ResourceManager
import ru.itis.liiceberg.common.model.TimeUnit
import ru.itis.liiceberg.common.model.TimeValues
import ru.itis.liiceberg.myplants_api.domain.model.MyPlant
import ru.itis.liiceberg.myplants_api.presenatation.MyPlantUiModel
import ru.itis.liiceberg.myplants_impl.R
import javax.inject.Inject

class MyPlantUiMapper @Inject constructor(
    private val resourceManager: ResourceManager,
) {
    fun mapMyPlantToMyPlantUiModel(plant: MyPlant): MyPlantUiModel {
        with(plant) {
            return MyPlantUiModel(
                id,
                name,
                scientificName,
                image,
                getPeriodInString(wateringPeriod),
                getPeriodInString(fertilizerPeriod)
            )
        }
    }

    private fun getPeriodInString(value: TimeValues?): String? {
        if (value == null) return null
        val id = when (value.periodUnit) {
            TimeUnit.DAYS -> R.string.care_in_days
            TimeUnit.WEEKS-> R.string.care_in_week
            TimeUnit.MONTHS -> R.string.care_in_month

        }
        return resourceManager.getString(id, value.periodValue)
    }
}