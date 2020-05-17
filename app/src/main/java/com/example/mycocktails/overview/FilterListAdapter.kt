package com.example.mycocktails.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mycocktails.databinding.LetterFilterViewItemBinding

class FilterListAdapter(private val onClickListener: OnClickListener):
    ListAdapter<Letter, FilterListAdapter.StringViewHolder>(DiffCallBack){
    class StringViewHolder(private var binding : LetterFilterViewItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(alphabet: Letter){
            binding.alphabet = alphabet
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Letter>() {
        override fun areItemsTheSame(oldItem: Letter, newItem: Letter): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Letter, newItem: Letter): Boolean {
            return oldItem.letter == newItem.letter
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        return StringViewHolder(LetterFilterViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        val alphabet : Letter = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.clickListener(alphabet)
        }
        holder.bind(alphabet)
    }

    class OnClickListener(val clickListener : (letter : Letter) -> Unit ){
        fun OnClick(alphabet: Letter) = clickListener(alphabet)
    }
}