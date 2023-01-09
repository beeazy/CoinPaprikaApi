package com.example.cleanapp.adapter

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanapp.databinding.CoincardBinding
import com.example.domain.model.Coin

class CoinRecyclerAdapter(val coinList: List<Coin>): RecyclerView.Adapter<CoinRecyclerAdapter.CoinViewHolder>() {
    class CoinViewHolder(val binding: CoincardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {

        val binding = CoincardBinding.inflate(
            LayoutInflater.from(parent.context
            ), parent, false)
        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val toast = Toast.makeText(it.context, coinList[position].name, Toast.LENGTH_SHORT)
            toast.setGravity(
                Gravity.START,200,200)
            toast.show()
        }
        holder.binding.coinSymbol.text = coinList[position].symbol
        holder.binding.coinName.text = coinList[position].name
        if (coinList[position].isActive) {
            holder.binding.coinPrice.text = "Active"
        } else {
            holder.binding.coinPrice.text = "Not active"
        }
        holder.binding.coinRank.text = coinList[position].rank
        holder.binding.coinType.text = coinList[position].type
    }

    override fun getItemCount() = coinList.size

}
