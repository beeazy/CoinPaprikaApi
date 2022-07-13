package com.example.data.extensions

import com.example.data.model.CoinModel
import com.example.domain.model.Coin

fun CoinModel.toCoin(): Coin {
    return Coin(
        id, name, symbol, isActive, rank, type
    )
}