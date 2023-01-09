package com.example.cleanapp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cleanapp.adapter.CoinRecyclerAdapter
import com.example.cleanapp.databinding.ActivityMainBinding
import com.example.cleanapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterr: CoinRecyclerAdapter
    private lateinit var viewModel: MainViewModel
    private val client = ConfigClient.client

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        fetchAndSet()

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

    private fun fetchAndSet() {
        client.getValueAsync(Boolean::class.java, "isMyFirstFeatureEnabled", false)
            .thenAccept { isMyFirstFeatureEnabled ->
                if (isMyFirstFeatureEnabled == true) {
                    println("isMyFirstFeatureEnabled is true")
                    // show the awesome feature
                    binding.textViewCat.text = "ConfigCat is enabled"
                } else {
                    println("isMyFirstFeatureEnabled is false")
                    // hide the awesome feature
                    binding.textViewCat.text = "ConfigCat is disabled"
                }
            }


    }
}