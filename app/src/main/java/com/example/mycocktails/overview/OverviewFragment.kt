package com.example.mycocktails.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mycocktails.databinding.FragmentOverviewBinding
import com.example.mycocktails.databinding.GridViewItemBinding

class OverviewFragment : Fragment() {

    private val viewModel : OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.cocktailGrid.adapter = CocktailsGridAdapter(CocktailsGridAdapter.OnClickListener{ cocktail ->
            viewModel.displayCocktailDetails(cocktail)
        })

        viewModel.navigateToSelectedCocktail.observe(this, Observer {cocktail ->
            if (null != cocktail){
                this.findNavController().navigate(OverviewFragmentDirections.actionShowCocktailDetails(cocktail ))
                viewModel.onDisplayedCocktailDetails()
            }
        })



        return binding.root
    }
}