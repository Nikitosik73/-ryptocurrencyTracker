package ru.paramonov.cryptocurrencytracker.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.paramonov.cryptocurrencytracker.domain.entity.CoinInfo

interface CoinRepository {

    fun getCoinInfoList(): Flow<List<CoinInfo>>

    fun getCoinInfo(fromSymbol: String): Flow<CoinInfo>

    fun loadData()
}