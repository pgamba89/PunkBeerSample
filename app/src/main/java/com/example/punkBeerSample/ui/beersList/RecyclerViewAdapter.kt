package com.example.punkBeerSample.ui.beersList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.punkBeerSample.databinding.FragmentRecyclerviewItemBinding
import com.example.punkBeerSample.model.BeerModel

class RecyclerViewAdapter(private val clickListener: ListItemListener) :
    PagingDataAdapter<BeerModel, RecyclerViewAdapter.ViewHolder>(ItemsDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: FragmentRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BeerModel, clickListener: ListItemListener) {
            binding.item = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FragmentRecyclerviewItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class ItemsDiffCallback : DiffUtil.ItemCallback<BeerModel>() {

    override fun areItemsTheSame(oldItem: BeerModel, newItem: BeerModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BeerModel, newItem: BeerModel): Boolean {
        return oldItem == newItem
    }
}

class ListItemListener(val clickListener: (beerModel: BeerModel) -> Unit) {
    fun onClick(beerModel: BeerModel) = clickListener(beerModel)
}