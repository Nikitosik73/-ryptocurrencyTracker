package ru.paramonov.cryptocurrencytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ru.paramonov.cryptocurrencytracker.presentation.screens.chat.ChatScreen
import ru.paramonov.cryptocurrencytracker.presentation.ui.theme.СryptocurrencyTrackerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            СryptocurrencyTrackerTheme {
                ChatScreen {}
            }
        }
    }
}
