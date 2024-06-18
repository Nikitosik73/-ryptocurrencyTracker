package ru.paramonov.cryptocurrencytracker.presentation.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.paramonov.cryptocurrencytracker.presentation.screens.navigation.screen.Screen

fun NavGraphBuilder.detailCoinNavGraph(
    detailCoinScreenContent: @Composable (String) -> Unit,
    chatScreenContent: @Composable (String) -> Unit
) {
    navigation(
        startDestination = Screen.DetailCoin.route,
        route = Screen.Home.route + "/coin"
    ) {
        composable(route = Screen.DetailCoin.route) { navBackStackEntry ->
            val coinName = navBackStackEntry.arguments?.getString(Screen.KEY_DETAIL_COIN_LIST) ?: ""
            detailCoinScreenContent(coinName)
        }
        composable(route = Screen.ChatCoinScreen.route) { navBackStackEntry ->
            val coinName = navBackStackEntry.arguments?.getString(Screen.KEY_CHAT_SCREEN) ?: ""
            chatScreenContent(coinName)
        }
    }
}