package ru.paramonov.cryptocurrencytracker.di

import android.app.Application
import dagger.Module
import dagger.Provides
import ru.paramonov.cryptocurrencytracker.data.database.AppDatabase
import ru.paramonov.cryptocurrencytracker.data.database.dao.CoinInfoDao
import ru.paramonov.cryptocurrencytracker.data.database.dao.NewsDao
import ru.paramonov.cryptocurrencytracker.di.annotation.ApplicationScope

@Module
class RoomModule {

    @Provides
    @ApplicationScope
    fun provideRoomDatabase(
        application: Application
    ): AppDatabase = AppDatabase.getInstance(application)

    @Provides
    @ApplicationScope
    fun provideCoinDao(
        database: AppDatabase
    ): CoinInfoDao = database.coinInfoDao()

    @Provides
    @ApplicationScope
    fun provideNewsApi(
        database: AppDatabase
    ): NewsDao = database.newsDao()
}