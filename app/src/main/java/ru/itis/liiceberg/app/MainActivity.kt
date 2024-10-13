package ru.itis.liiceberg.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.itis.liiceberg.app.di.Navigator
import ru.itis.liiceberg.common.navigation.Route
import ru.itis.liiceberg.ui.theme.AppTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            AppTheme {
                Navigation()
            }
        }
    }

    @Composable
    private fun Navigation() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.Auth.SignIn,
        ) {
            with(navigator.auth) {
                registerGraph(navController)
            }
        }
    }

}