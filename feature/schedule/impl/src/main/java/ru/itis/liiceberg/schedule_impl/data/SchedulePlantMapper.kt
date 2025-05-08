package ru.itis.liiceberg.schedule_impl.data

import ru.itis.liiceberg.data.db.model.FavouritePlant
import ru.itis.liiceberg.data.db.model.Plant
import ru.itis.liiceberg.schedule_api.domain.model.SchedulePlant
import javax.inject.Inject

class SchedulePlantMapper @Inject constructor() {

    fun mapPlantAndFavouriteToSchedulePlant(plant: Plant?, favourite: FavouritePlant?) : SchedulePlant {
        return SchedulePlant(
            favourite?.id ?: "",
            plant?.name ?: "",
            plant?.scientificName ?: "",
            plant?.image?.firstOrNull() ?: "",
            plant?.water ?: "",
            plant?.fertilization ?: "",
            favourite?.wateringPeriod,
            favourite?.fertilizerPeriod,
        )
    }

}