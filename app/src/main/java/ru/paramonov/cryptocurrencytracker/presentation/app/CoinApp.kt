package ru.paramonov.cryptocurrencytracker.presentation.app

import android.app.Application
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import ru.paramonov.cryptocurrencytracker.di.ApplicationComponent
import ru.paramonov.cryptocurrencytracker.di.DaggerApplicationComponent


class CoinApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }
}

@Composable
fun getApplicationComponent(): ApplicationComponent {
    Log.d("test_recomposition", "getApplicationComponent")
    return (LocalContext.current.applicationContext as CoinApp).component
}