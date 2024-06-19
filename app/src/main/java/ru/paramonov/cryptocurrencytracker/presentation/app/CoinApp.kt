package ru.paramonov.cryptocurrencytracker.presentation.app

import android.app.Application
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.work.Configuration
import ru.paramonov.cryptocurrencytracker.data.workers.CoinWorkerFactory
import ru.paramonov.cryptocurrencytracker.di.ApplicationComponent
import ru.paramonov.cryptocurrencytracker.di.DaggerApplicationComponent
import javax.inject.Inject

class CoinApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: CoinWorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory).build()
    }
}

@Composable
fun getApplicationComponent(): ApplicationComponent {
    Log.d("test_recomposition", "getApplicationComponent")
    return (LocalContext.current.applicationContext as CoinApp).component
}