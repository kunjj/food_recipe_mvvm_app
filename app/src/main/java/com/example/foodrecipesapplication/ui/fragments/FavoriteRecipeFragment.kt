package com.example.foodrecipesapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
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
    private lateinit var menuHost: MenuHost

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        this.binding = FragmentFavoriteRecipeBinding.inflate(inflater)
        this.binding!!.lifecycleOwner = this
        this.binding!!.foodRecipeViewModel = this.foodRecipesViewModel
        this.menuHost = requireActivity()
        (activity as RecipeActivity).setSupportActionBar(binding!!.toolbar)
        (activity as RecipeActivity).supportActionBar?.title =
            context?.getString(R.string.favorite_recipes)
        return this.binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMenuBar()
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

    private fun setMenuBar() {
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) =
                menuInflater.inflate(R.menu.favorite_recipe_menu, menu)

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.delete_all_favorite_recipes -> {
                        if (favoriteRecipeAdapter.favoriteRecipes.currentList.isEmpty()) {
                            showSnackBar(
                                requireView(),
                                getString(R.string.theres_no_favorite_recipes)
                            )
                            false
                        } else {
                            favoriteRecipeAdapter.favoriteRecipes.submitList(emptyList())
                            foodRecipesViewModel.deleteAllFavoriteRecipe()
                            showSnackBar(requireView(), getString(R.string.all_recipes_removed))
                            true
                        }
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.binding = null
        this.favoriteRecipeAdapter.clearSelection()
    }
}