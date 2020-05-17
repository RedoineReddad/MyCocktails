package com.example.mycocktails.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"
// Moshi Builder to convert Json to Result object
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Retrofit Builder using Moshi Json converter and Coroutine Adapter to use Kotlin coroutines
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

// API service interface to provide GET requests
interface CocktailsApiService{
    @GET("search.php")
    fun getAllCocktails(@Query("s")type :String):
            Deferred<Result>

    @GET("search.php")
    fun getCocktailsByLetter(@Query("f")letter : String):
            Deferred<Result>
}
// Singleton object to initialize API service
object CocktailsApi{
    val retrofitService : CocktailsApiService by lazy { retrofit.create(CocktailsApiService::class.java) }
}