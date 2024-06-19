package ru.paramonov.cryptocurrencytracker.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import ru.paramonov.cryptocurrencytracker.data.repository.CoinRepositoryImpl
import ru.paramonov.cryptocurrencytracker.data.repository.NewsRepositoryImpl
import ru.paramonov.cryptocurrencytracker.domain.repository.CoinRepository
import ru.paramonov.cryptocurrencytracker.domain.repository.NewsRepository

@Module
interface DataModule {

    @Binds
    fun bindCoinRepository(
        impl: CoinRepositoryImpl
    ): CoinRepository

    @Binds
    fun bindNewsRepository(
        impl: NewsRepositoryImpl
    ): NewsRepository

    companion object {

        @Provides
        fun provideCoroutineScope(): CoroutineScope {
            return CoroutineScope(Dispatchers.IO)
        }
    }
}