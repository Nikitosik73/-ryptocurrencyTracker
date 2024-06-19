package ru.paramonov.cryptocurrencytracker.domain.entity

import androidx.compose.runtime.Immutable

@Immutable
data class NewsInfo(
    val id: Int,
    val guid: String,
    val imageUrl: String,
    val title: String,
    val body: String,
    val titleNews: String
)