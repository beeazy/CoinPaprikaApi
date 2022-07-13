package com.example.data.model

import com.google.gson.annotations.SerializedName

data class CoinModel(
    val id: String,
    val name: String,
    val symbol: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val rank: String,
    val type: String,
)
