package ru.paramonov.cryptocurrencytracker.domain.entity

import androidx.compose.runtime.Immutable

@Immutable
data class CoinInfo(
    val fromSymbol: String,
    val toSymbol: String?,
    val price: String?,
    val lastUpdate: String,
    val highDay: String?,
    val lowDay: String?,
    val lastMarket: String?,
    val imageUrl: String
)
