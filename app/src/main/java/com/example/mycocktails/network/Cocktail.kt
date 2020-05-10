package com.example.mycocktails.network

import com.squareup.moshi.Json

// class to hold information about cocktails
data class Cocktail(
    @Json(name = "idDrink") val id : String,
    @Json(name = "strDrink") val name : String,
    @Json(name = "strInstructions") val instructions : String,
    @Json(name = "strDrinkThumb") val imgSrcUrl: String
)