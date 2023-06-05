package com.senpro.ulamsae.ui.history

import android.graphics.BitmapFactory
import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.senpro.ulamsae.databinding.ItemMarketBinding
import com.senpro.ulamsae.model.Market
import com.senpro.ulamsae.model.response.User
import com.senpro.ulamsae.ui.camera.rotateBitmap

class MarketAdapter : RecyclerView.Adapter<MarketAdapter.ListViewHolder>() {

    private val listMarket = mutableListOf<Market>()

    fun setList(market: List<Market>) {
        val oldSize = listMarket.size
        listMarket.clear()
        listMarket.addAll(market)
        val newSize = listMarket.size
        notifyItemRangeRemoved(0, oldSize)
        notifyItemRangeInserted(0, newSize)
    }

    inner class ListViewHolder(private var binding: ItemMarketBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(market: Market) {
            val result = rotateBitmap(
                BitmapFactory.decodeFile(market.file.path)
            )

            binding.apply {
                imgItemPhoto.setImageBitmap(result)
                tvNama.text = market.fileName
                tvHarga.text = "Rp${market.harga}"
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding =
            ItemMarketBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listMarket[position])
    }

    override fun getItemCount(): Int = listMarket.size
}