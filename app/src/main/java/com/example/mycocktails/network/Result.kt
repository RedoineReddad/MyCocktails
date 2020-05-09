package com.example.mycocktails.network

// class to hold intermediary Result containing List<Cocktail> due to the API response architecture
data class Result(
    val drinks : List<Cocktail>
)


