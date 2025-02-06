package ru.itis.liiceberg.myplants_api.domain.model

data class MyPlant(
    val id: String,
    val name: String,
    val scientificName: String,
    val image: String,
    val location: String,
    val watering: Int,
    val fertilizer: Int,
)
