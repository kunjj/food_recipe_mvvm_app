package com.example.foodrecipesapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodrecipesapplication.adapters.FavoriteRecipeAdapter
import com.example.foodrecipesapplication.databinding.FragmentFavoriteRecipeBinding
import com.example.foodrecipesapplication.ui.activities.RecipeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteRecipeFragment : BaseFragment() {
    private var binding: FragmentFavoriteRecipeBinding? = null
    private val foodRecipesViewModel by lazy { (activity as RecipeActivity).foodRecipesViewModel }
    private val favoriteRecipeAdapter by lazy { FavoriteRecipeAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        this.binding = FragmentFavoriteRecipeBinding.inflate(inflater)
        return this.binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.foodRecipesViewModel.favoriteRecipes.observe(viewLifecycleOwner) { favoriteRecipes ->
            if (favoriteRecipes.isNotEmpty()) this.favoriteRecipeAdapter.favoriteRecipes.submitList(
                favoriteRecipes.toList()
            )
        }

        this.binding!!.rvFavoriteRecipes.apply {
            adapter = favoriteRecipeAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        this.binding = null
    }
}