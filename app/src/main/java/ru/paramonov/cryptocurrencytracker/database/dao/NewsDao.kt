package ru.paramonov.cryptocurrencytracker.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.paramonov.cryptocurrencytracker.database.model.NewsDBO

@Dao
interface NewsInfoDao {

    @Query("select * from news")
    fun getAllNews(): Flow<List<NewsDBO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsList(listNews: List<NewsDBO>)
}