package com.example.foodrecipesapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodrecipesapplication.R
import com.example.foodrecipesapplication.adapters.FavoriteRecipeAdapter
import com.example.foodrecipesapplication.databinding.FragmentFavoriteRecipeBinding
import com.example.foodrecipesapplication.ui.activities.RecipeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteRecipeFragment : BaseFragment() {
    var binding: FragmentFavoriteRecipeBinding? = null
    private val foodRecipesViewModel by lazy { (activity as RecipeActivity).foodRecipesViewModel }
    private val favoriteRecipeAdapter by lazy {
        FavoriteRecipeAdapter(
            requireActivity(), foodRecipesViewModel
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        this.binding = FragmentFavoriteRecipeBinding.inflate(inflater)
        this.binding!!.lifecycleOwner = this
        this.binding!!.foodRecipeViewModel = this.foodRecipesViewModel
        setHasOptionsMenu(true)
        (activity as RecipeActivity).setSupportActionBar(binding!!.toolbar)
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

    override fun onCreateOptionsMenu(menu: Menu, menuIinflater: MenuInflater) =
        menuIinflater.inflate(R.menu.favorite_recipe_menu, menu)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_all_favorite_recipes -> {
                foodRecipesViewModel.deleteAllFavoriteRecipe()
                showSnackBar(requireView(), getString(R.string.all_recipes_removed))
                true
            }

            else -> false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.binding = null
        this.favoriteRecipeAdapter.clearSelection()
    }
}