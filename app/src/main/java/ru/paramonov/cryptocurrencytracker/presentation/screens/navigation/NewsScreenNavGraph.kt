package ru.paramonov.cryptocurrencytracker.presentation.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.paramonov.cryptocurrencytracker.presentation.screens.navigation.screen.Screen

fun NavGraphBuilder.newsScreenNavGraph(
    newsScreenContent: @Composable () -> Unit,
    detailNewsScreenContent: @Composable (String) -> Unit
) {
    navigation(
        startDestination = Screen.NewsScreenContent.route,
        route = Screen.NewsScreen.route
    ) {
        composable(Screen.NewsScreenContent.route) {
            newsScreenContent()
        }
        composable(route = Screen.DetailNewsScreen.route) { navBackStackEntry ->

            detailNewsScreenContent("args.news")
        }
    }
}