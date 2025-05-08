package ru.itis.liiceberg.data.db.model

import ru.itis.liiceberg.common.model.TimeValues

data class FavouritePlant(
    var id: String? = null,
    val userId: String = "",
    val plantId: String = "",
    val wateringPeriod: TimeValues? = null,
    val fertilizerPeriod: TimeValues? = null,
)
