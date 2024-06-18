package ru.paramonov.cryptocurrencytracker.presentation.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.paramonov.cryptocurrencytracker.presentation.screens.navigation.screen.Screen

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    coinListScreenContent: @Composable () -> Unit,
    detailCoinScreenContent: @Composable (String) -> Unit,
    newsScreenContent: @Composable () -> Unit,
    detailNewsScreenContent: @Composable (String) -> Unit,
    chatScreenContent: @Composable (String) -> Unit,
    profileScreenContent: @Composable () -> Unit
) {

    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {
        homeScreenNavGraph(
            coinListScreenContent = coinListScreenContent,
            detailCoinScreenContent = detailCoinScreenContent,
            chatScreenContent = chatScreenContent
        )
        newsScreenNavGraph(
            newsScreenContent = newsScreenContent,
            detailNewsScreenContent = detailNewsScreenContent
        )
        composable(Screen.ProfileScreen.route) {
            profileScreenContent()
        }
    }
}