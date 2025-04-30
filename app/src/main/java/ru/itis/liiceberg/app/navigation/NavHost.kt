package ru.itis.liiceberg.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ru.itis.liiceberg.common.navigation.Route


@Composable
fun NavHostContainer(
    navController: NavHostController,
    navigator: Navigator,
    startDestination: Route,
    onBottomBarVisibilityChanged: (Boolean) -> Unit,
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        with(navigator.auth) {
            registerGraph(navController, onBottomBarVisibilityChanged)
        }
        with(navigator.explore) {
            registerGraph(navController, onBottomBarVisibilityChanged)
        }
        with(navigator.myPlants) {
            registerGraph(navController, onBottomBarVisibilityChanged)
        }
        with(navigator.settings) {
            registerGraph(navController, onBottomBarVisibilityChanged)
        }
        with(navigator.reminder) {
            registerGraph(navController, onBottomBarVisibilityChanged)
        }
        with(navigator.schedule) {
            registerGraph(navController, onBottomBarVisibilityChanged)
        }
    }
}
