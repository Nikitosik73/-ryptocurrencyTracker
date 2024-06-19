package ru.paramonov.cryptocurrencytracker.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.paramonov.cryptocurrencytracker.di.annotation.ViewModelKey
import ru.paramonov.cryptocurrencytracker.presentation.screens.coinlist.CoinViewModel
import ru.paramonov.cryptocurrencytracker.presentation.screens.news.NewsViewModel

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(CoinViewModel::class)
    @Binds
    fun bindCoinViewModel(
        impl: CoinViewModel
    ): ViewModel

    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    @Binds
    fun bindNewsViewModel(
        impl: NewsViewModel
    ): ViewModel
}