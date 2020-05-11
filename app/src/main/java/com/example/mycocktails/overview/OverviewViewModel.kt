package com.example.mycocktails.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mycocktails.network.Cocktail
import com.example.mycocktails.network.CocktailApiStatus
import com.example.mycocktails.network.CocktailsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {

    // Variables to handle errors with data binding
    private val _status = MutableLiveData<CocktailApiStatus>()
    val status : LiveData<CocktailApiStatus>
        get() = _status

    // variables to store the list of cocktail to be displayed in the recyclerView
    private val _cocktails = MutableLiveData<List<Cocktail>>()
    val cocktails : LiveData<List<Cocktail>>
        get() = _cocktails

    // variables to handle navigation and pass data to detailFragment
    private val _navigateToSelectedCocktail = MutableLiveData<Cocktail>()
    val navigateToSelectedCocktail : LiveData<Cocktail>
        get () = _navigateToSelectedCocktail

    // Job and coroutine Scope to allow usage of Kotlin coroutine for network call on other threads
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    // OverviewViewModel constructor
    init {
        getCocktailsDetails()
    }

    // Deferred call on
    private fun getCocktailsDetails() {
        var getCocktailsDeferred = CocktailsApi.retrofitService.getCocktails("")
        coroutineScope.launch {
            try {
                _status.value = CocktailApiStatus.LOADING
                var result = getCocktailsDeferred.await()
                if (result.drinks.isNotEmpty()){
                    _cocktails.value = result.drinks
                    _status.value = CocktailApiStatus.DONE
                }
            }catch (t : Throwable){
                _status.value = CocktailApiStatus.ERROR
            }
        }
    }

    fun displayCocktailDetails(cocktail: Cocktail){
        _navigateToSelectedCocktail.value = cocktail
    }

    fun onDisplayedCocktailDetails(){
        _navigateToSelectedCocktail.value = null
    }
    // onCleared method to cancel Job and stop loading data
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}