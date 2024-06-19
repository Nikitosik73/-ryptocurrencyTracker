package ru.paramonov.cryptocurrencytracker.domain

import ru.paramonov.cryptocurrencytracker.domain.repository.CoinRepository

class GetCoinInfoListUseCase(
    private val repository: CoinRepository
) {

    operator fun invoke() = repository.getCoinInfoList()
}