package com.example.mycocktails.network


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

// class to hold information about cocktails
@Parcelize
data class Cocktail(
    @Json(name = "idDrink") val id : String,
    @Json(name = "strDrink") val name : String,
    @Json(name = "strInstructions") val instructions : String,
    @Json(name = "strDrinkThumb") val imgSrcUrl: String
) : Parcelable