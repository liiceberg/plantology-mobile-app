package ru.itis.liiceberg.app

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import ru.itis.liiceberg.common.navigation.Route
import ru.itis.liiceberg.data.storage.UserDataStore
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userDataStore: UserDataStore,
) : ViewModel() {

    fun getStartDestination(): Route {
        return runBlocking {
            if (userDataStore.getUserId() != null) {
                Route.BottomMenu.MyPlants
            } else {
                Route.Auth.SignIn
            }
        }
    }
}