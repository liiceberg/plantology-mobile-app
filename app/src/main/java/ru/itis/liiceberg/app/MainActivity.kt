package ru.itis.liiceberg.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.util.DebugLogger
import dagger.hilt.android.AndroidEntryPoint
import ru.itis.liiceberg.app.navigation.BottomNavigationBar
import ru.itis.liiceberg.app.navigation.NavHostContainer
import ru.itis.liiceberg.app.navigation.Navigator
import ru.itis.liiceberg.ui.theme.AppTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            AppTheme {
                val imageLoader = ImageLoader.Builder(LocalContext.current)
                    .logger(DebugLogger())
                    .build()
                setSingletonImageLoaderFactory {
                    imageLoader
                }

                val navController = rememberNavController()
                var bottomBarVisible by remember { mutableStateOf(true) }
                Surface(color = MaterialTheme.colorScheme.surface) {
                    Scaffold(
                        bottomBar = {
                            if (bottomBarVisible) {
                                BottomNavigationBar(navController = navController)
                            }
                        },
                        content = { paddingValues ->
                            Box(modifier = Modifier.padding(paddingValues)) {
                                NavHostContainer(
                                    navController = navController,
                                    navigator = navigator,
                                ) { isVisible ->
                                    bottomBarVisible = isVisible
                                }
                            }
                        }
                    )
                }
            }
        }
    }

}