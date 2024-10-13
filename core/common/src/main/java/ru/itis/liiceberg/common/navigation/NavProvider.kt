package ru.itis.liiceberg.common.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

sealed interface NavProvider {
    fun NavGraphBuilder.registerGraph(controller: NavHostController)
}

interface AuthNavProvider : NavProvider
