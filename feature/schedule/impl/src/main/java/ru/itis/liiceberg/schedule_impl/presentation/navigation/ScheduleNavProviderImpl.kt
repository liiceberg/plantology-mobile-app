package ru.itis.liiceberg.schedule_impl.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import ru.itis.liiceberg.common.navigation.Route
import ru.itis.liiceberg.common.navigation.ScheduleNavProvider
import ru.itis.liiceberg.schedule_impl.presentation.screens.ChangeScheduleView
import javax.inject.Inject

class ScheduleNavProviderImpl @Inject constructor(): ScheduleNavProvider {

    override fun NavGraphBuilder.registerGraph(
        controller: NavHostController,
        onBottomBarVisibilityChanged: (Boolean) -> Unit
    ) {
        composable<Route.EditSchedule> { backStackEntry ->
            onBottomBarVisibilityChanged(false)
            val args = backStackEntry.toRoute<Route.EditSchedule>()
            ChangeScheduleView(plantId = args.plantId) {
                controller.navigateUp()
            }
        }
    }
}