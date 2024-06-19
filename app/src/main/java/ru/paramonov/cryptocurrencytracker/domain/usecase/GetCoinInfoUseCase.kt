package ru.paramonov.cryptocurrencytracker.domain.usecase

import ru.paramonov.cryptocurrencytracker.domain.repository.CoinRepository

class GetCoinInfoUseCase(
    private val repository: CoinRepository
) {

    operator fun invoke(fromSymbol: String) = repository.getCoinInfo(fromSymbol = fromSymbol)
}