package com.example.mycocktails.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mycocktails.databinding.GridViewItemBinding
import com.example.mycocktails.network.Cocktail

class CocktailsGridAdapter : ListAdapter<Cocktail, CocktailsGridAdapter.CocktailViewHolder>(DiffCallBack) {
    class CocktailViewHolder(private var binding : GridViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cocktail: Cocktail){
            binding.cocktail = cocktail
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Cocktail>() {
        override fun areItemsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        return CocktailViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        val cocktail = getItem(position)
        holder.bind(cocktail)
    }
}