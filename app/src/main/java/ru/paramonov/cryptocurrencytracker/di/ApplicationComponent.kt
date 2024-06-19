package com.example.cryptoapp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.paramonov.cryptocurrencytracker.di.DataModule
import ru.paramonov.cryptocurrencytracker.di.RetrofitModule
import ru.paramonov.cryptocurrencytracker.di.RoomModule
import ru.paramonov.cryptocurrencytracker.di.ViewModelModule
import ru.paramonov.cryptocurrencytracker.di.WorkerModule
import ru.paramonov.cryptocurrencytracker.di.annotation.ApplicationScope

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

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}
