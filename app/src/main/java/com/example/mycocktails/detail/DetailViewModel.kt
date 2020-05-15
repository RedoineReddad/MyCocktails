package com.example.mycocktails.detail

import android.app.Application
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.lifecycle.*
import com.example.mycocktails.network.Cocktail

class DetailViewModel(cocktail: Cocktail, application: Application) : ViewModel() {

    // variables to store the Cocktail's details to be displayed
    private val _selectedCocktail = MutableLiveData<Cocktail>()
    val selectedCocktail : LiveData<Cocktail>
        get() = _selectedCocktail
    private val selectedCocktailIngredients : List<String>
        get() = makeList(requireNotNull(_selectedCocktail.value))

    val ingredientsString : Spanned?
        get() = formatString(selectedCocktailIngredients)

    private fun makeList(cocktail: Cocktail) : List<String>{
        return cocktail.measures.zip(cocktail.ingredients) { a, b ->
            if (a != "") "$a - $b" else b
        }.dropLastWhile { it == "" }
    }

    private fun formatString(list : List<String>) : Spanned{
        val sb = StringBuilder()
        sb.apply {
            list.forEach {
                sb.append("\t- $it<br>")
            }
        }
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
        } else {

            HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)

        }
    }

    // initialize LiveData with attribute given in constructor
    init {
        _selectedCocktail.value = cocktail
    }
}