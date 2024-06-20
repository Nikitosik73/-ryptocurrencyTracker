package ru.paramonov.cryptocurrencytracker.presentation.screens.navigation.screen

import android.net.Uri
import com.google.gson.Gson
import ru.paramonov.cryptocurrencytracker.domain.entity.NewsInfo

sealed class Screen(
    val route: String
) {

    data object Home : Screen(route = ROUTE_HOME)

    data object CoinList : Screen(route = ROUTE_COIN_LIST)

    data object DetailCoin : Screen(route = ROUTE_DETAIL_COIN) {

        private const val ARGS_ROUTE_DETAIL_COIN = "coin_detail"

        fun getRouteWithArgs(coinName: String): String {
            return "$ARGS_ROUTE_DETAIL_COIN/$coinName"
        }
    }

    data object NewsScreen : Screen(route = ROUTE_NEWS)

    data object NewsScreenContent : Screen(route = ROUTE_NEWS_SCREEN_CONTENT)

    data object DetailNewsScreen : Screen(route = ROUTE_DETAIL_NEWS_SCREEN) {

        private const val ARGS_ROUTE_DETAIL_NEWS = "news_detail"

        fun getRouteWithArgs(newsInfo: NewsInfo): String {
            val news = Gson().toJson(newsInfo).encode()
            return "$ARGS_ROUTE_DETAIL_NEWS/$news"
        }
    }

    data object ChatCoinScreen : Screen(route = ROUTE_CHAT_SCREEN) {

        private const val ARGS_ROUTE_CHAT = "chat_screen"

        fun getRouteWithArgs(coinName: String): String {
            return "$ARGS_ROUTE_CHAT/$coinName"
        }
    }

    data object ProfileScreen : Screen(route = ROUTE_PROFILE)

    companion object {

        const val KEY_DETAIL_COIN_LIST = "coinName"
        const val KEY_DETAIL_NEWS = "news"
        const val KEY_CHAT_SCREEN = "coinName"

        private const val ROUTE_HOME = "home"
        private const val ROUTE_COIN_LIST = "coin_list"
        private const val ROUTE_DETAIL_COIN = "coin_detail/{$KEY_DETAIL_COIN_LIST}"
        private const val ROUTE_NEWS = "news"
        private const val ROUTE_NEWS_SCREEN_CONTENT = "news_screen_content"
        private const val ROUTE_DETAIL_NEWS_SCREEN = "news_detail/{$KEY_DETAIL_NEWS}"
        private const val ROUTE_PROFILE = "profile"
        private const val ROUTE_CHAT_SCREEN = "chat_screen/{$KEY_DETAIL_COIN_LIST}"
    }
}

private fun String.encode() = Uri.encode(this)
