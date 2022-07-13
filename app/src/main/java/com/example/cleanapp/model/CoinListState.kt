package com.example.cleanapp.model

import com.example.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val coins: List<Coin> = emptyList()
)
