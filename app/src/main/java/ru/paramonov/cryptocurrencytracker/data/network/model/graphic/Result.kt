package ru.paramonov.cryptocurrencytracker.data.network.model.graphic

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("results") val resultListBars: List<Bar>
)
