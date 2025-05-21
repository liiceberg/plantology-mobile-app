package ru.itis.liiceberg.myplants_api.domain.model

import ru.itis.liiceberg.common.model.TimeValues

data class MyPlant(
    val favId: String,
    val plantId: String,
    val name: String,
    val scientificName: String,
    val image: String,
    val wateringPeriod: TimeValues?,
    val fertilizerPeriod: TimeValues?,
)
