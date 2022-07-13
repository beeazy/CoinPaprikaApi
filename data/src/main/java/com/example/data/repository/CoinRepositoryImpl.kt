package com.example.data.repository

import com.example.data.api.CoinPaprikaApi
import com.example.data.extensions.toCoin
import com.example.domain.model.Coin
import com.example.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
): CoinRepository {
    override suspend fun getCoins(): List<Coin> {
        try {
            return api.getCoins().map { it.toCoin() }
        } catch (e: Exception) {
            throw e
        }
    }
}