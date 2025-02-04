package ru.itis.liiceberg.common.navigation

import kotlinx.serialization.Serializable

sealed class Route {

    object BottomMenu {
        @Serializable
        data object Explore : Route()
        @Serializable
        data object MyPlants : Route()
    }

    object Auth {
        @Serializable
        data object SignIn : Route()

        @Serializable
        data object SignUp : Route()
    }

    @Serializable
    data class PlantDetails(val plantId: String) : Route()

    object Settings {

        @Serializable
        data object Main : Route()

        @Serializable
        data object ChangePassword : Route()
    }
}