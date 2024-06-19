package ru.paramonov.cryptocurrencytracker.data.network.model.news

import com.google.gson.annotations.SerializedName

data class TitleNewsDto(
    @SerializedName("name")
    val name: String
)
