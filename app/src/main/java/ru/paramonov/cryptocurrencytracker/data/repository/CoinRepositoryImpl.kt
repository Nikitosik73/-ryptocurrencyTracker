package ru.paramonov.cryptocurrencytracker.data.repository

import android.app.Application
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.cryptoapp.data.mapper.CoinMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.paramonov.cryptocurrencytracker.data.database.dao.CoinInfoDao
import ru.paramonov.cryptocurrencytracker.data.workers.RefreshDataWorker
import ru.paramonov.cryptocurrencytracker.domain.entity.CoinInfo
import ru.paramonov.cryptocurrencytracker.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val application: Application,
    private val mapper: CoinMapper,
    private val coinDao: CoinInfoDao
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
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}