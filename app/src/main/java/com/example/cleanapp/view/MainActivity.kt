package com.example.cleanapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.cleanapp.R
import com.example.cleanapp.adapter.CoinRecyclerAdapter
import com.example.cleanapp.databinding.ActivityMainBinding
import com.example.cleanapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CoinRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.state.observe(this) {
            if (!it.isLoading) {
                binding.progressBar.visibility = android.view.View.INVISIBLE

                binding.textError.text = it.error
                binding.textError.visibility = android.view.View.VISIBLE
            } else {
                binding.progressBar.visibility = android.view.View.VISIBLE
                binding.textError.visibility = android.view.View.INVISIBLE

                adapter = CoinRecyclerAdapter(it.coins)
                binding.recyclerView.adapter = adapter
            }

        }
    }
}