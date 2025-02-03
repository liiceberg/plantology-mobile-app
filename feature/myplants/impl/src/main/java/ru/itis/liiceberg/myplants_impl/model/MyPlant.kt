package ru.itis.liiceberg.myplants_impl.model

data class MyPlant(
    val name: String,
    val scientificName: String,
    val image: String,
    val location: String,
    val watering: Int,
    val fertilizer: Int
)
