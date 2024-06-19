package ru.paramonov.cryptocurrencytracker.presentation.application

import android.app.Application
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import ru.paramonov.cryptocurrencytracker.di.ApplicationComponent
import ru.paramonov.cryptocurrencytracker.di.DaggerApplicationComponent

class NewsFeedApplication : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(application = this)
    }
}

@Composable
fun getApplicationComponent(): ApplicationComponent {
    Log.d("test_recomposition", "getApplicationComponent")
    return (LocalContext.current.applicationContext as NewsFeedApplication).component
}