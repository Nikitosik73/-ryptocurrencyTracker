package ru.paramonov.cryptocurrencytracker.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.paramonov.cryptocurrencytracker.data.database.model.NewsDBO

@Dao
interface NewsDao {

    @Query("select * from news")
    fun getAllNews(): Flow<List<NewsDBO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsList(listNews: List<NewsDBO>)
}