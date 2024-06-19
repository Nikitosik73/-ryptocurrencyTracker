package ru.paramonov.cryptocurrencytracker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.paramonov.cryptocurrencytracker.presentation.app.getApplicationComponent
import ru.paramonov.cryptocurrencytracker.presentation.screens.coinlist.CoinViewModel
import ru.paramonov.cryptocurrencytracker.presentation.ui.theme.СryptocurrencyTrackerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            СryptocurrencyTrackerTheme {
//                var isAuth by remember { mutableStateOf(false) }
//
//                if (isAuth) {
//                    MainScreen()
//                } else {
//                    RegistrationScreen(onRegistrationClick = { isAuth = true })
//                }
                Column(
                    modifier = Modifier.fillMaxSize().background(color = Color.LightGray)
                ) {
                    val component = getApplicationComponent()
                    val viewModel: CoinViewModel = viewModel(factory = component.getViewModel())
                    val coinInfo = viewModel.coinInfoList.collectAsStateWithLifecycle(initialValue = emptyList())

                    Log.d("coin_worker", coinInfo.value.toString())
                }
            }
        }
    }
}
