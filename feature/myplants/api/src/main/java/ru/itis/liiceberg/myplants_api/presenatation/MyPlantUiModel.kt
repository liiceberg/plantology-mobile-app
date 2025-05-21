package ru.itis.liiceberg.myplants_api.presenatation

data class MyPlantUiModel(
    val id: String,
    val plantId: String,
    val name: String,
    val scientificName: String,
    val image: String,
    val watering: String?,
    val fertilizer: String?,
)