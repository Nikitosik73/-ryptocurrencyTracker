package ru.paramonov.cryptocurrencytracker.domain

import ru.paramonov.cryptocurrencytracker.domain.repository.NewsRepository

class GetNewsUseCase(
    private val repository: NewsRepository
) {

    operator fun invoke() = repository.getNews()
}