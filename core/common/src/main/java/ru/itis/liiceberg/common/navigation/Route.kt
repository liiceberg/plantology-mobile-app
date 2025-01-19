package ru.itis.liiceberg.common.navigation

import kotlinx.serialization.Serializable

sealed class Route {

    object BottomMenu {
        @Serializable
        data object Main : Route()
    }

    object Auth {
        @Serializable
        data object SignIn : Route()

        @Serializable
        data object SignUp : Route()
    }
}