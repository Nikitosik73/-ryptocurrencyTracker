package ru.paramonov.cryptocurrencytracker.domain.usecase

import ru.paramonov.cryptocurrencytracker.domain.repository.CoinRepository
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke() = repository.loadData()
}