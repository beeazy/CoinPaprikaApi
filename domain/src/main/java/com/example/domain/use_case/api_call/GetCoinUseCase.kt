package com.example.domain.use_case.api_call

import android.util.Log
import com.example.domain.common.Resource
import com.example.domain.model.Coin
import com.example.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
//        emit(Resource.Loading())
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins()
            emit(Resource.Success(coins))
        } catch (e: IOException) {
            Log.e("GetCoinUseCase", e.message ?: "Error occured hereeee")
            emit(Resource.Error("Network Error Occurred!"))
        }
    }
}