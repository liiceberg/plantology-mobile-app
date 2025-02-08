package ru.itis.liiceberg.explore_impl.data

import ru.itis.liiceberg.data.db.model.Plant
import ru.itis.liiceberg.explore_api.domain.model.ExplorePlantModel
import ru.itis.liiceberg.explore_api.domain.model.PlantModel
import javax.inject.Inject

class ExploreMapper @Inject constructor() {

    fun mapPlantToExplorePlant(plant: Plant): ExplorePlantModel {
        return ExplorePlantModel(
            id = plant.id ?: "",
            name = plant.name ?: "",
            scientificName = plant.scientificName ?: "",
            imageUrl = plant.image!!.first { it.isNotBlank() },
        )
    }

    fun mapFirebasePlantToPlantModel(plant: Plant?, saved: Boolean?): PlantModel {
        plant?.run {
            return PlantModel(
                id = id ?: "",
                name = name ?: "",
                scientificName = scientificName ?: "",
                image = image ?: emptyList(),
                description = description ?: "",
                family = family ?: "",
                water = water ?: "",
                hardinessZones = hardinessZones ?: "",
                higherClassification = higherClassification ?: "",
                humidity = humidity ?: "",
                kingdom = kingdom ?: "",
                order = order ?: "",
                rank = rank ?: "",
                sunlight = sunlight ?: "",
                temperature = temperature?.info ?: "",
                maxTemperature = temperature?.max,
                minTemperature = temperature?.min,
                toxicity = toxicity,
                saved = saved ?: false
            )
        }
        throw NullPointerException()
    }

}