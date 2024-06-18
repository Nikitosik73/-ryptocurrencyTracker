package ru.paramonov.cryptocurrencytracker.presentation.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.paramonov.cryptocurrencytracker.presentation.screens.navigation.screen.Screen

class NavigationState(
    val navHostController: NavHostController
) {

    fun navigateTo(route: String) {
        navHostController.navigate(route = route) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToDetailCoin(coinName: String) {
        navHostController.navigate(route = Screen.DetailCoin.getRouteWithArgs(coinName = coinName))
    }

    fun navigateToChat(coinName: String) {
        navHostController.navigate(route = Screen.ChatCoinScreen.getRouteWithArgs(coinName = coinName))
    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState = remember {
    NavigationState(navHostController = navHostController)
}