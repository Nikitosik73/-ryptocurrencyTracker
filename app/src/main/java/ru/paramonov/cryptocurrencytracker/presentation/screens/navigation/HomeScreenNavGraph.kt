package ru.paramonov.cryptocurrencytracker.presentation.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.paramonov.cryptocurrencytracker.presentation.screens.navigation.screen.Screen

fun NavGraphBuilder.homeScreenNavGraph(
    coinListScreenContent: @Composable () -> Unit,
    detailCoinScreenContent: @Composable (String) -> Unit,
    chatScreenContent: @Composable (String) -> Unit
) {
    navigation(
        startDestination = Screen.CoinList.route,
        route = Screen.Home.route
    ) {
        composable(route = Screen.CoinList.route) {
            coinListScreenContent()
        }
        detailCoinNavGraph(
            detailCoinScreenContent = detailCoinScreenContent,
            chatScreenContent = chatScreenContent
        )
    }
}