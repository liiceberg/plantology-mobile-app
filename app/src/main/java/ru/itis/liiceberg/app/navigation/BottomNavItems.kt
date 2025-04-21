package ru.itis.liiceberg.app.navigation

import ru.itis.liiceberg.app.R
import ru.itis.liiceberg.common.navigation.Route
import ru.itis.liiceberg.ui.components.BottomNavItem


val bottomNavItems = listOf(
    BottomNavItem(
        label = R.string.label_reminder,
        icon = R.drawable.alarm,
        route = getRoute(Route.BottomMenu.Reminder),
    ),
    BottomNavItem(
        label = R.string.label_explore,
        icon = R.drawable.search,
        route = getRoute(Route.BottomMenu.Explore),
    ),
    BottomNavItem(
        label = R.string.label_my_plants,
        icon = R.drawable.leaf,
        route = getRoute(Route.BottomMenu.MyPlants),
    ),
)

private fun getRoute(route: Route): String {
    return route::class.qualifiedName.toString()
}
