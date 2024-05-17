package com.example.foodrecipesapplication.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipesapplication.models.Recipe
import com.example.foodrecipesapplication.room.entities.FavoriteRecipe
import com.example.foodrecipesapplication.ui.fragments.FavoriteRecipeFragmentDirections

class FavoriteRecipesBindingAdapter {
    companion object {
        @BindingAdapter("setDataVisibility")
        @JvmStatic
        fun setDataVisibility(view: View, favoriteRecipes: List<FavoriteRecipe>?) {
            when(view){
                is RecyclerView -> view.isInvisible = favoriteRecipes.isNullOrEmpty()
                else -> view.isVisible = favoriteRecipes.isNullOrEmpty()
            }
        }

        @BindingAdapter("favoriteRecipeOnClickListener")
        @JvmStatic
        fun favoriteRecipeOnClickListener(constraintLayout: ConstraintLayout, recipe: Recipe) {
            constraintLayout.setOnClickListener {
                val action =
                    FavoriteRecipeFragmentDirections.actionFavoriteRecipeFragmentToDetailsActivity(
                        recipe
                    )
                constraintLayout.findNavController().navigate(action)
            }
        }
    }
}