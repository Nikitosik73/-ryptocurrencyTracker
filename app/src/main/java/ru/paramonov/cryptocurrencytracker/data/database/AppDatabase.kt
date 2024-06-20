package ru.paramonov.cryptocurrencytracker.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.paramonov.cryptocurrencytracker.data.database.dao.CoinInfoDao
import ru.paramonov.cryptocurrencytracker.data.database.dao.NewsDao
import ru.paramonov.cryptocurrencytracker.data.database.model.CoinInfoDBO
import ru.paramonov.cryptocurrencytracker.data.database.model.NewsDBO

@Database(entities = [CoinInfoDBO::class, NewsDBO::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    abstract fun coinInfoDao(): CoinInfoDao

    companion object {
        private const val NAME_DB = "main.db"
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            instance?.let { return it }
            synchronized(LOCK) {
                instance?.let { return it }
                val db = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    NAME_DB
                ).fallbackToDestructiveMigration().build()
                instance = db
                return db
            }
        }
    }
}