package ru.itis.liiceberg.myplants_impl.data

import ru.itis.liiceberg.data.db.model.FavouritePlant
import ru.itis.liiceberg.data.db.model.Plant
import ru.itis.liiceberg.myplants_api.domain.model.MyPlant
import javax.inject.Inject

class MyPlantsMapper @Inject constructor() {
    fun mapPlantAndFavouritePlantToMyPlant(plant: Plant?, favourite: FavouritePlant?) : MyPlant {
        return MyPlant(
            favId = favourite?.id ?: "",
            plantId = plant?.id ?: "",
            name = plant?.name ?: "",
            scientificName = plant?.scientificName ?: "",
            image = plant?.image?.firstOrNull() ?: "",
            wateringPeriod = favourite?.wateringPeriod,
            fertilizerPeriod = favourite?.fertilizerPeriod,
        )
    }
}