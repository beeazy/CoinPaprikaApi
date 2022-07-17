package com.example.cleanapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanapp.model.CoinListState
import com.example.domain.common.Resource
import com.example.domain.use_case.api_call.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase
): ViewModel() {
    private val _state = MutableLiveData<CoinListState>()
    val state: LiveData<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())

                    println("${_state.value}")
                }
                is Resource.Error -> {
                    _state.value = CoinListState(error = result.message ?: "Error occurred")
                }
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}