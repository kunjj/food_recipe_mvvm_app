package com.example.foodrecipesapplication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.foodrecipesapplication.R
import com.example.foodrecipesapplication.databinding.FragmentRecipeBinding

class RecipeFragment : Fragment() {
    lateinit var binding : FragmentRecipeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_recipe, container, false)
        binding.recyclerView.shimmerItemCount = 10
        binding.recyclerView.showShimmer()
        return binding.root
    }

}