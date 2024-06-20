package ru.paramonov.cryptocurrencytracker.domain.entity

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Serializable
@Immutable
data class NewsInfo(
    val id: Int,
    val guid: String,
    val imageUrl: String,
    val title: String,
    val body: String,
    val titleNews: String
)