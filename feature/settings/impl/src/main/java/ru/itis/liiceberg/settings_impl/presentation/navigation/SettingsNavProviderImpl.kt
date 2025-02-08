package ru.itis.liiceberg.settings_impl.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.itis.liiceberg.common.navigation.Route
import ru.itis.liiceberg.common.navigation.SettingsNavProvider
import ru.itis.liiceberg.settings_impl.presentation.screens.change_password.ChangePasswordView
import ru.itis.liiceberg.settings_impl.presentation.screens.settings.SettingsView
import javax.inject.Inject

class SettingsNavProviderImpl @Inject constructor() : SettingsNavProvider {

    override fun NavGraphBuilder.registerGraph(
        controller: NavHostController,
        onBottomBarVisibilityChanged: (Boolean) -> Unit
    ) {

        composable<Route.Settings.Main> {
            onBottomBarVisibilityChanged(false)
            SettingsView(
                toChangePassword = { controller.navigate(Route.Settings.ChangePassword) },
                onLogOut = { controller.navigate(Route.Auth.SignIn) },
                onBack = { controller.navigateUp() }
            )
        }

        composable<Route.Settings.ChangePassword> {
            onBottomBarVisibilityChanged(false)
            ChangePasswordView(
                goBack = { controller.navigateUp() }
            )
        }
    }
}