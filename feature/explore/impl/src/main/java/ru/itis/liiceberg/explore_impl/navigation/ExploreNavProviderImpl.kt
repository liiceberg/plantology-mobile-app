package ru.itis.liiceberg.explore_impl.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import ru.itis.liiceberg.common.navigation.ExploreNavProvider
import ru.itis.liiceberg.common.navigation.Route
import ru.itis.liiceberg.explore_impl.screens.details.PlantsDetailsView
import ru.itis.liiceberg.explore_impl.screens.explore.ExploreView
import javax.inject.Inject

class ExploreNavProviderImpl @Inject constructor() : ExploreNavProvider {

    override fun NavGraphBuilder.registerGraph(
        controller: NavHostController,
        onBottomBarVisibilityChanged: (Boolean) -> Unit
    ) {
        composable<Route.BottomMenu.Explore> {
            onBottomBarVisibilityChanged(true)
            ExploreView { plantId ->
                controller.navigate(Route.PlantDetails(plantId))
            }
        }

        composable<Route.PlantDetails> { backStackEntry ->
            onBottomBarVisibilityChanged(false)
            val args = backStackEntry.toRoute<Route.PlantDetails>()
            PlantsDetailsView(plantId = args.plantId) {
                controller.navigateUp()
            }
        }
    }

}