package ru.itis.liiceberg.explore_api.domain.model

data class PlantModel(
    val id: String,
    val name: String,
    val scientificName: String,
    val image: List<String>,
    val description: String,
    val family: String,
    val water: String,
    val hardinessZones: String,
    val higherClassification: String,
    val humidity: String,
    val kingdom: String,
    val order: String,
    val rank: String,
    val sunlight: String,
    val temperature: String,
    val maxTemperature: Int?,
    val minTemperature: Int?,
    val toxicity: Boolean?,
    val saved: Boolean,
) {
//    fun empty(): PlantModel {
//        return PlantModel(
//        )
//    }
}