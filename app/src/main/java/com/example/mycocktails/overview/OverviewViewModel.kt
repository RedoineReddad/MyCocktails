package com.example.mycocktails.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mycocktails.network.Result
import com.example.mycocktails.network.CocktailsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverviewViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response : LiveData<String>
        get() = _response

    init {
        getCocktailsDetails()
    }

    private fun getCocktailsDetails() {
        CocktailsApi.retrofitService.getCocktails("").enqueue(object : Callback<Result>{
            override fun onFailure(call: Call<Result>, t: Throwable) {
                _response.value = "Failure : " + t.message
            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                _response.value = "Success ${response.body()?.drinks?.size} cocktails were found"
            }
        })
    }
}