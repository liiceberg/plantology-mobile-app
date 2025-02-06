package ru.itis.liiceberg.data.db.model

data class FavouritePlant(
    val userId: String = "",
    val plantId: String = "",
    val watering: Int = 0,
    val fertilizer: Int = 0,
)