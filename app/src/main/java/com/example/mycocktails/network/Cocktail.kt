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
    @Json(name = "strDrinkThumb") val imgSrcUrl: String,
    @Json(name = "strIngredient1") val ingredient1 : String?,
    @Json(name = "strIngredient2") val ingredient2 : String?,
    @Json(name = "strIngredient3") val ingredient3 : String?,
    @Json(name = "strIngredient4") val ingredient4 : String?,
    @Json(name = "strIngredient5") val ingredient5 : String?,
    @Json(name = "strIngredient6") val ingredient6: String?,
    @Json(name = "strMeasure1") val measure1: String?,
    @Json(name = "strMeasure2") val measure2: String?,
    @Json(name = "strMeasure3") val measure3: String?,
    @Json(name = "strMeasure4") val measure4: String?,
    @Json(name = "strMeasure5") val measure5: String?,
    @Json(name = "strMeasure6") val measure6: String?,
    val ingredients : List<String> =
        listOf(ingredient1 ?: "",
            ingredient2 ?: "",
            ingredient3 ?: "",
            ingredient4 ?: "",
            ingredient5 ?: "",
            ingredient6 ?: ""),
    val measures : List<String> =
        listOf(measure1 ?: "",
            measure2 ?: "",
            measure3 ?: "",
            measure4 ?: "",
            measure5 ?: "",
            measure6 ?: "")
) : Parcelable