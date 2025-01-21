package ru.itis.liiceberg.app.navigation

import ru.itis.liiceberg.common.navigation.AuthNavProvider
import ru.itis.liiceberg.common.navigation.MyPlantsNavProvider

data class Navigator(
    val auth: AuthNavProvider,
    val myPlants: MyPlantsNavProvider
)
