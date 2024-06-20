package ru.paramonov.cryptocurrencytracker.presentation.screens.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.paramonov.cryptocurrencytracker.domain.usecase.GetNewsUseCase
import ru.paramonov.cryptocurrencytracker.domain.usecase.LoadNewsUseCase
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val loadNewsUseCase: LoadNewsUseCase
) : ViewModel() {

    val newsList = getNewsUseCase()

    init {
        loadNews()
    }

    private fun loadNews() {
        viewModelScope.launch {
            loadNewsUseCase()
        }
    }
}