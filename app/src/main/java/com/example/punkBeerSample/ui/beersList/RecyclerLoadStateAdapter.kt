package com.example.punkBeerSample.ui.beersList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.punkBeerSample.R
import com.example.punkBeerSample.utils.Constants.Companion.BEER_SEARCH_ERROR
import kotlinx.android.synthetic.main.load_state_view_item.view.*

class RecyclerLoadStateAdapter :
    LoadStateAdapter<RecyclerLoadStateAdapter.LoadingStateViewHolder>() {

    class LoadingStateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvErrorMessage: TextView = itemView.tvErrorMessage

        fun bindState(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                tvErrorMessage.text = BEER_SEARCH_ERROR
            }
            tvErrorMessage.isVisible = loadState !is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) {
        holder.bindState(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingStateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.load_state_view_item, parent, false)
        return LoadingStateViewHolder(view)
    }

}