package ru.paramonov.cryptocurrencytracker.presentation.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.gson.Gson
import ru.paramonov.cryptocurrencytracker.domain.entity.NewsInfo
import ru.paramonov.cryptocurrencytracker.presentation.screens.navigation.screen.Screen

fun NavGraphBuilder.newsScreenNavGraph(
    newsScreenContent: @Composable () -> Unit,
    detailNewsScreenContent: @Composable (NewsInfo) -> Unit
) {
    navigation(
        startDestination = Screen.NewsScreenContent.route,
        route = Screen.NewsScreen.route
    ) {
        composable(Screen.NewsScreenContent.route) {
            newsScreenContent()
        }
        composable(route = Screen.DetailNewsScreen.route) { navBackStackEntry ->
            val newsJson = navBackStackEntry.arguments?.getString(Screen.KEY_DETAIL_NEWS) ?: ""
            val news = Gson().fromJson(newsJson, NewsInfo::class.java)
            detailNewsScreenContent(news)
        }
    }
}