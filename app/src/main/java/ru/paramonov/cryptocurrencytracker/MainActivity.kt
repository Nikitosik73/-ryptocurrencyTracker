package ru.paramonov.cryptocurrencytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import ru.paramonov.cryptocurrencytracker.presentation.screens.main.MainScreen
import ru.paramonov.cryptocurrencytracker.presentation.screens.registaration.RegistrationScreen
import ru.paramonov.cryptocurrencytracker.presentation.ui.theme.СryptocurrencyTrackerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            СryptocurrencyTrackerTheme {
                var isAuth by remember { mutableStateOf(false) }

                if (isAuth) {
                    MainScreen()
                } else {
                    RegistrationScreen(onRegistrationClick = { isAuth = true })
                }
            }
        }
    }
}
