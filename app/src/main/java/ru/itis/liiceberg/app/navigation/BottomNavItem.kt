package ru.itis.liiceberg.app.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.itis.liiceberg.app.R
import ru.itis.liiceberg.common.navigation.Route

data class BottomNavItem(
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
    val route: String,
)


val BottomNavItems = listOf(
    BottomNavItem(
        label = R.string.label_my_plants,
        icon = R.drawable.leaf,
        route = Route.BottomMenu.MyPlants.toString(),
    ),
)