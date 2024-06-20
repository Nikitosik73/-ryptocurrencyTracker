package ru.paramonov.cryptocurrencytracker.presentation.screens.coinlist

import ru.paramonov.cryptocurrencytracker.R

object CoinList {

    data class Coin(
        val id: Int,
        val name: String,
        val price: String,
        val symbol: String,
        val imageCoin: Int
    )

    val coinList = listOf(
        Coin(
            id = 0,
            name = "Bitcoin",
            price = "26.64",
            symbol = "BTC/BUSD",
            imageCoin = R.drawable.bitcoin
        ),
        Coin(
            id = 1,
            name = "Etherium",
            price = "37.61",
            symbol = "ETH/BUSD",
            imageCoin = R.drawable.etherium
        ),
        Coin(
            id = 2,
            name = "Litecoin",
            price = "207.3",
            symbol = "LTC/BUSD",
            imageCoin = R.drawable.litecoin
        )
    )
}