package ru.paramonov.cryptocurrencytracker.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.paramonov.cryptocurrencytracker.di.annotation.ApplicationScope
import ru.paramonov.cryptocurrencytracker.presentation.ViewModelFactory
import ru.paramonov.cryptocurrencytracker.presentation.app.CoinApp

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
        WorkerModule::class,
        RetrofitModule::class,
        RoomModule::class
    ]
)
interface ApplicationComponent {

    fun getViewModel(): ViewModelFactory

    fun inject(application: CoinApp)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}
