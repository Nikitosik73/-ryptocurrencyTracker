package ru.paramonov.cryptocurrencytracker.data.network.model.coinname

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinNamesListDto(
    @SerializedName("Data")
    @Expose
    val names: List<CoinNamesContainerDto>? = null
)
