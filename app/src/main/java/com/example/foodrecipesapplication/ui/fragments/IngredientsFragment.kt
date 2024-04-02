package com.example.foodrecipesapplication.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodrecipesapplication.adapters.IngredientsAdapter
import com.example.foodrecipesapplication.databinding.FragmentIngredientsBinding
import com.example.foodrecipesapplication.models.Recipe

class IngredientsFragment : Fragment() {

    private lateinit var binding: FragmentIngredientsBinding
    private lateinit var recipe: Recipe
    private val ingredientsAdapter by lazy { IngredientsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        this.binding = FragmentIngredientsBinding.inflate(inflater)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.recipe = requireArguments().getParcelable("recipe", Recipe::class.java)!!

        this.ingredientsAdapter.ingredients.submitList(this.recipe.extendedIngredients?.toList())

        this.binding.rvIngredients.apply {
            adapter = this@IngredientsFragment.ingredientsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}