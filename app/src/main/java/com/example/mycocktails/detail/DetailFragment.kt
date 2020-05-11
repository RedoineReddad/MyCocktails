package com.example.mycocktails.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mycocktails.databinding.FragmentDetailBinding

class DetailFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)
        val cocktail = DetailFragmentArgs.fromBundle(requireArguments()).selectedCocktail

        val viewModel = ViewModelProvider(this, DetailViewModelFactory(cocktail, application)).get(DetailViewModel::class.java)

        binding.viewModel = viewModel

        return binding.root
    }
}