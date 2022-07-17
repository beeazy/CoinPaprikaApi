package com.example.data.api

//import com.example.domain.model.Coin
import com.example.data.model.CoinModel
import retrofit2.http.GET

interface CoinPaprikaApi {
    @GET("v1/coins")
    suspend fun getCoins(): List<CoinModel>
}