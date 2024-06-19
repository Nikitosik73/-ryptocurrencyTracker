package com.example.cryptoapp.data.repository

import com.example.cryptoapp.data.mapper.NewsMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.paramonov.cryptocurrencytracker.data.database.dao.NewsDao
import ru.paramonov.cryptocurrencytracker.data.network.apiservice.NewsApi
import ru.paramonov.cryptocurrencytracker.domain.entity.NewsInfo
import ru.paramonov.cryptocurrencytracker.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsDao: NewsDao,
    private val mapper: NewsMapper,
    private val newsApi: NewsApi,
    private val coroutineScope: CoroutineScope
) : NewsRepository {

    override fun getNews(): Flow<List<NewsInfo>> {
        val news = newsDao.getAllNews()
        return news.map { listNewsDbModels ->
            listNewsDbModels.map { dbModel ->
                mapper.mapDbModelToEntity(dbModel)
            }
        }
    }

    override suspend fun loadNews() {
        coroutineScope.launch {
            val newsContainer = newsApi.getNewsCoin()
            val listNewsDto = mapper.mapNewsContainerToListNews(newsContainer)
            val listDbModel = listNewsDto.map { dto ->
                mapper.mapDtoToDbModel(dto)
            }
            newsDao.insertNewsList(listDbModel)
        }
    }
}