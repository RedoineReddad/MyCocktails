package com.example.mycocktails.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cocktail(
    val idDrink : String,
    val strDrink : String,
    val strInstructions : String,
    val strIngredients : List<String>
) : Parcelable