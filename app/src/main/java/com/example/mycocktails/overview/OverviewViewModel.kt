package com.example.mycocktails.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mycocktails.network.Cocktail
import com.example.mycocktails.network.CocktailsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {

    // Variable to handle errors
    private val _status = MutableLiveData<String>()
    val status : LiveData<String>
        get() = _status


    private val _cocktail = MutableLiveData<Cocktail>()
    val cocktail : LiveData<Cocktail>
        get() = _cocktail

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
                var result = getCocktailsDeferred.await()
                if (result.drinks.isNotEmpty()){
                    _cocktail.value = result.drinks[0]
                }
            }catch (t : Throwable){
                _status.value = "Failure : " + t.message
            }
        }
    }
    // onCleared method to cancel Job and stop loading data
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}