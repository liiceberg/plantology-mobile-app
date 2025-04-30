package ru.itis.liiceberg.myplants_impl.presentation.mapper

import ru.itis.liiceberg.common.resources.ResourceManager
import ru.itis.liiceberg.common.util.getValueInString
import ru.itis.liiceberg.myplants_api.domain.model.MyPlant
import ru.itis.liiceberg.myplants_api.presenatation.MyPlantUiModel
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
                wateringPeriod?.getValueInString(resourceManager),
                fertilizerPeriod?.getValueInString(resourceManager)
            )
        }
    }
}