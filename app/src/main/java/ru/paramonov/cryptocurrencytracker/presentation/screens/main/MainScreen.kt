package ru.paramonov.cryptocurrencytracker.presentation.screens.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.paramonov.cryptocurrencytracker.presentation.screens.ProfileScreen
import ru.paramonov.cryptocurrencytracker.presentation.screens.chat.ChatScreen
import ru.paramonov.cryptocurrencytracker.presentation.screens.coinlist.CoinListScreen
import ru.paramonov.cryptocurrencytracker.presentation.screens.deteilcoin.DetailCoinInfo
import ru.paramonov.cryptocurrencytracker.presentation.screens.navigation.AppNavGraph
import ru.paramonov.cryptocurrencytracker.presentation.screens.navigation.NavItem
import ru.paramonov.cryptocurrencytracker.presentation.screens.navigation.NavigationState
import ru.paramonov.cryptocurrencytracker.presentation.screens.navigation.rememberNavigationState


@Composable
fun MainScreen() {

    val navigationState = rememberNavigationState()

    Scaffold(
        bottomBar = { BottomNavBar(navigationState = navigationState) }
    ) { innerPadding ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            coinListScreenContent = {
                CoinListScreen(
                    paddingValues = innerPadding,
                    onClickCoin = { coinName ->
                        navigationState.navigateToDetailCoin(coinName = coinName)
                    }
                )
            },
            detailCoinScreenContent = { fromSymbol ->
                DetailCoinInfo(
                    paddingValues = innerPadding,
                    fromSymbol = fromSymbol,
                    onClickChatCoin = {
                        navigationState.navigateToChat(coinName = fromSymbol)
                    }
                )
            },
            newsScreenContent = {

            },
            detailNewsScreenContent = {

            },
            chatScreenContent = {
                ChatScreen(coinName = it) {
                    navigationState.navHostController.popBackStack()
                }
            },
            profileScreenContent = {
                ProfileScreen(paddingValues = innerPadding)
            }
        )
    }
}

@Composable
private fun BottomNavBar(navigationState: NavigationState) {

    val items = listOf(
        NavItem.Home,
        NavItem.News,
        NavItem.Profile
    )

    Row(
        modifier = Modifier
            .padding(vertical = 20.dp, horizontal = 10.dp)
            .clip(shape = CircleShape)
            .background(color = Color.White)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items.forEach { item ->
                BottomNavItem(
                    navigationState = navigationState,
                    item = item
                )
            }
        }
    }
}

@Composable
private fun BottomNavItem(
    navigationState: NavigationState,
    item: NavItem
) {
    val navBackStackEntry
            by navigationState.navHostController.currentBackStackEntryAsState()

    val selectedItem = navBackStackEntry?.destination?.hierarchy?.any {
        it.route == item.screen.route
    } == true

    val background =
        if (selectedItem) Color(0xFF5EDE99) else Color.Transparent

    val contentColor =
        if (selectedItem) Color.White else Color.Black

    Box(
        modifier = Modifier
            .height(40.dp)
            .clip(shape = CircleShape)
            .background(color = background)
            .clickable { navigationState.navigateTo(item.screen.route) },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = if (selectedItem) item.focusedIcon else item.unfocusedIcon,
                contentDescription = null,
                tint = contentColor
            )
            AnimatedVisibility(visible = selectedItem) {
                Text(
                    text = stringResource(id = item.titleResId),
                    color = contentColor
                )
            }
        }
    }
}