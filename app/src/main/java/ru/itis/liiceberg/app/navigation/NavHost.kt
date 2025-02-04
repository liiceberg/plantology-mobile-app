package ru.itis.liiceberg.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ru.itis.liiceberg.common.navigation.Route


@Composable
fun NavHostContainer(
    navController: NavHostController,
    navigator: Navigator,
    onBottomBarVisibilityChanged: (Boolean) -> Unit,
) {

    NavHost(
        navController = navController,
        startDestination = Route.BottomMenu.MyPlants,
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
        }

}