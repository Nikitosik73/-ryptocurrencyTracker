package ru.paramonov.cryptocurrencytracker.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.paramonov.cryptocurrencytracker.domain.entity.NewsInfo

interface NewsRepository {

    fun getNews(): Flow<List<NewsInfo>>

    suspend fun loadNews()
}