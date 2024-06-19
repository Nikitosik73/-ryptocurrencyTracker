package ru.paramonov.cryptocurrencytracker.domain.usecase

import ru.paramonov.cryptocurrencytracker.domain.repository.NewsRepository

class GetNewsUseCase(
    private val repository: NewsRepository
) {

    operator fun invoke() = repository.getNews()
}