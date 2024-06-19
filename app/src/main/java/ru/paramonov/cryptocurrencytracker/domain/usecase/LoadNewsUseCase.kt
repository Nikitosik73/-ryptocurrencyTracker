package ru.paramonov.cryptocurrencytracker.domain.usecase

import ru.paramonov.cryptocurrencytracker.domain.repository.NewsRepository
import javax.inject.Inject

class LoadNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    suspend operator fun invoke() = repository.loadNews()
}