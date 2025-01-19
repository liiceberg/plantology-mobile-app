package ru.itis.liiceberg.auth_impl.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.itis.liiceberg.auth_impl.screens.sign_in.SignInView
import ru.itis.liiceberg.auth_impl.screens.sign_up.SignUpView
import ru.itis.liiceberg.common.navigation.AuthNavProvider
import ru.itis.liiceberg.common.navigation.Route
import javax.inject.Inject

class AuthNavProviderImpl @Inject constructor() : AuthNavProvider {

    override fun NavGraphBuilder.registerGraph(controller: NavHostController) {
        composable<Route.Auth.SignIn> {
            SignInView(
                toSignUp = { controller.navigate(Route.Auth.SignUp) },
                toMainPage = { controller.navigate(Route.BottomMenu.Main) }
            )
        }
        composable<Route.Auth.SignUp> {
            SignUpView {
                controller.navigateUp()
            }
        }
    }

}