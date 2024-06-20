package ru.paramonov.cryptocurrencytracker.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.paramonov.cryptocurrencytracker.data.network.apiservice.ApiService

object ApiFactory {

    private val BASE_URL = "https://api.polygon.io/v2/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val apiService = retrofit.create<ApiService>()
}