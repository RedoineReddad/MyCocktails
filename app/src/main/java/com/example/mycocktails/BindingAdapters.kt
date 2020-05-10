package com.example.mycocktails

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mycocktails.network.Cocktail
import com.example.mycocktails.network.CocktailApiStatus
import com.example.mycocktails.overview.CocktailsGridAdapter

// Binding function to provide image from URL to an ImageView
@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl:String?){
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imgUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imageView)
    }
}

// Binding function to provide item from a List<Cocktail> to a RecyclerView
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, cocktailList: List<Cocktail>?){
    val adapter = recyclerView.adapter as CocktailsGridAdapter
    adapter.submitList(cocktailList)
}

@BindingAdapter("cocktailApiStatus")
fun bindStatus(statusImgView : ImageView, status: CocktailApiStatus?){
    when(status){
        CocktailApiStatus.LOADING -> {
            statusImgView.visibility  = View.VISIBLE
            statusImgView.setImageResource(R.drawable.loading_animation)
        }
        CocktailApiStatus.ERROR -> {
            statusImgView.visibility = View.VISIBLE
            statusImgView.setImageResource(R.drawable.ic_connection_error)
        }
        CocktailApiStatus.DONE-> statusImgView.visibility = View.GONE
    }
}