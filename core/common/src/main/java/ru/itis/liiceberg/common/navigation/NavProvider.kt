package ru.itis.liiceberg.common.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

sealed interface NavProvider {

    fun NavGraphBuilder.registerGraph(
        controller: NavHostController,
        onBottomBarVisibilityChanged: (Boolean) -> Unit,
    )

}

interface AuthNavProvider : NavProvider

interface ExploreNavProvider : NavProvider

interface MyPlantsNavProvider : NavProvider
