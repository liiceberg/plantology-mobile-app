package ru.itis.liiceberg.myplants_impl.data

import ru.itis.liiceberg.data.db.model.FavouritePlant
import ru.itis.liiceberg.data.db.model.Plant
import ru.itis.liiceberg.myplants_api.domain.model.MyPlant
import javax.inject.Inject

class MyPlantsMapper @Inject constructor() {
    fun mapPlantAndFavouritePlantToMyPlant(plant: Plant?, favourite: FavouritePlant?) : MyPlant {
        return MyPlant(
            id = plant?.id ?: "",
            name = plant?.name ?: "",
            scientificName = plant?.scientificName ?: "",
            image = plant?.image?.firstOrNull() ?: "",
//            fertilizer = favourite?.fertilizer ?: 0,
//            watering = favourite?.watering ?: 0,
            fertilizer = 4,
            watering = 1,
            location = "",
        )
    }
}