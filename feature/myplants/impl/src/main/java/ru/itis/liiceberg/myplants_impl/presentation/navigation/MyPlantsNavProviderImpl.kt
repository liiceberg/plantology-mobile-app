package ru.itis.liiceberg.myplants_impl.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.itis.liiceberg.common.navigation.MyPlantsNavProvider
import ru.itis.liiceberg.common.navigation.Route
import ru.itis.liiceberg.myplants_impl.presentation.screens.MyPlantsView
import javax.inject.Inject

class MyPlantsNavProviderImpl @Inject constructor() : MyPlantsNavProvider {

    override fun NavGraphBuilder.registerGraph(
        controller: NavHostController,
        onBottomBarVisibilityChanged: (Boolean) -> Unit
    ) {
        composable<Route.BottomMenu.MyPlants> {
            onBottomBarVisibilityChanged(true)
            MyPlantsView(
                goToSettings = { controller.navigate(Route.Settings.Main) },
                goToExplore = { controller.navigate(Route.BottomMenu.Explore) },
                goToEditSchedule = { controller.navigate(Route.EditSchedule(it)) },
            )
        }

    }

}