package ru.paramonov.cryptocurrencytracker.presentation.screens.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import ru.paramonov.cryptocurrencytracker.R
import ru.paramonov.cryptocurrencytracker.presentation.screens.navigation.screen.Screen

sealed class NavItem(
    val screen: Screen,
    val titleResId: Int,
    val unfocusedIcon: ImageVector,
    val focusedIcon: ImageVector
) {
    data object Home : NavItem(
        screen = Screen.Home,
        titleResId = R.string.home_screen,
        unfocusedIcon = Icons.Outlined.ShoppingCart,
        focusedIcon = Icons.Rounded.ShoppingCart
    )

    data object News : NavItem(
        screen = Screen.NewsScreen,
        titleResId = R.string.news_screen,
        unfocusedIcon = Icons.Outlined.MailOutline,
        focusedIcon = Icons.Rounded.Email
    )

    data object Profile : NavItem(
        screen = Screen.ProfileScreen,
        titleResId = R.string.profile_screen,
        unfocusedIcon = Icons.Outlined.AccountCircle,
        focusedIcon = Icons.Rounded.AccountCircle
    )
}