package ru.itis.liiceberg.reminder_impl.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.itis.liiceberg.common.navigation.ReminderNavProvider
import ru.itis.liiceberg.common.navigation.Route
import ru.itis.liiceberg.reminder_impl.presentation.screens.ReminderView
import javax.inject.Inject

class ReminderNavProviderImpl @Inject constructor() : ReminderNavProvider {

    override fun NavGraphBuilder.registerGraph(
        controller: NavHostController,
        onBottomBarVisibilityChanged: (Boolean) -> Unit
    ) {
        composable<Route.BottomMenu.Reminder> {
            onBottomBarVisibilityChanged(true)
            ReminderView(
                navigateToExplore = { controller.navigate(Route.BottomMenu.Explore) }
            )
        }
    }
}