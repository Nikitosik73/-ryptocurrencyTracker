package ru.paramonov.cryptocurrencytracker.data.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.paramonov.cryptocurrencytracker.data.database.dao.CoinInfoDao
import ru.paramonov.cryptocurrencytracker.data.mapper.CoinMapper
import ru.paramonov.cryptocurrencytracker.data.network.apiservice.ApiService
import ru.paramonov.cryptocurrencytracker.domain.entity.CoinInfo
import ru.paramonov.cryptocurrencytracker.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val mapper: CoinMapper,
    private val coinDao: CoinInfoDao,
    private val scope: CoroutineScope,
    private val apiService: ApiService
) : CoinRepository {

    override fun getCoinInfoList(): Flow<List<CoinInfo>> {
        val coinNames = coinDao.getCoinNamesList()
        return coinNames.map { listDbModels ->
            listDbModels.map { dbModel ->
                mapper.mapDbModelToEntity(dbModel)
            }
        }
    }

    override fun getCoinInfo(fromSymbol: String): Flow<CoinInfo> {
        val coinInfo = coinDao.getInfoAboutCoin(fromSymbol)
        return coinInfo.map { dbModel ->
            mapper.mapDbModelToEntity(dbModel)
        }
    }

    override fun loadData() {
        scope.launch {
            while (true) {
                try {
                    val topCoins = apiService.getTopCoinsInfo(limit = 50)
                    val coinNames = mapper.mapNamesListToString(topCoins)
                    val jsonContainer = apiService.getFullPriceList(fSyms = coinNames)
                    val listCoinInfoDto = mapper.mapJsonContainerToDto(jsonContainer)
                    val listDbModels = listCoinInfoDto.map { dto ->
                        mapper.mapDtoToDbModel(dto)
                    }
                    coinDao.insertPriceList(listDbModels)
                } catch (_: Exception) {
                }
                delay(10000)
            }
        }
    }
}