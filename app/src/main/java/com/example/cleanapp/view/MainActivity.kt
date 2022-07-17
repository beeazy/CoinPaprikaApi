package com.example.cleanapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.cleanapp.R
import com.example.cleanapp.adapter.CoinRecyclerAdapter
import com.example.cleanapp.databinding.ActivityMainBinding
import com.example.cleanapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterr: CoinRecyclerAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.state.observe(this) {
            if (!it.isLoading) {
                binding.progressBar.visibility = View.INVISIBLE
                if (it.error.isNotBlank()) {
                    binding.textError.visibility = View.VISIBLE
                    binding.textError.text = it.error
                } else {
                    binding.recyclerView.visibility = View.VISIBLE
                    adapterr = CoinRecyclerAdapter(it.coins)
                    binding.recyclerView.adapter = adapterr
                }
            }

        }
    }
}