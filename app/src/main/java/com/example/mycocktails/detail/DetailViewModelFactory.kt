package com.example.mycocktails.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mycocktails.network.Cocktail


@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(
    private val cocktail: Cocktail,
    private val application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(cocktail, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}