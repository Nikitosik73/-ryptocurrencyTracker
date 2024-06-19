package ru.paramonov.cryptocurrencytracker.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.paramonov.cryptocurrencytracker.database.model.CoinInfoDBO

@Dao
interface CoinInfoDao {
    @Query("SELECT * FROM full_price_list ORDER BY lastUpdate desc")
    fun getCoinNamesList(): Flow<List<CoinInfoDBO>>

    @Query("SELECT * FROM full_price_list WHERE fromSymbol == :fSym LIMIT 1")
    fun getInfoAboutCoin(fSym: String): Flow<CoinInfoDBO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPriceList(priceList: List<CoinInfoDBO>)
}