package com.example.mycocktails.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mycocktails.network.Cocktail

class DetailViewModel(cocktail: Cocktail, application: Application) : ViewModel() {

    // variable to store the Cocktail's details to be displayed
    private val _selectedCocktail = MutableLiveData<Cocktail>()
    val selectedCocktail : LiveData<Cocktail>
        get() = _selectedCocktail

    // initialize LiveData with attribute given in constructor
    init {
        _selectedCocktail.value = cocktail
    }
}