package com.example.foodrecipesapplication.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.example.foodrecipesapplication.R
import com.example.foodrecipesapplication.models.FoodRecipe
import com.example.foodrecipesapplication.models.Recipe
import com.example.foodrecipesapplication.network.NetworkResponse
import com.example.foodrecipesapplication.room.entities.FoodRecipeEntity
import com.example.foodrecipesapplication.ui.fragments.RecipeFragmentDirections

class RecipeBindingAdapter {
    companion object {
        @BindingAdapter("recipeOnClickListener")
        @JvmStatic
        fun recipeOnClick(recipeRowLayout: ConstraintLayout, recipe: Recipe) =
            recipeRowLayout.setOnClickListener {
                val action = RecipeFragmentDirections.actionRecipeFragmentToDetailsActivity(recipe)
                recipeRowLayout.findNavController().navigate(action)
            }

        @BindingAdapter("apiResponse", "database", requireAll = true)
        @JvmStatic
        fun notConnectedToInternetError(
            linearLayout: LinearLayout,
            apiResponse: NetworkResponse<FoodRecipe>?,
            database: List<FoodRecipeEntity>?
        ) {
            if (apiResponse != null && apiResponse is NetworkResponse.Error && database.isNullOrEmpty()) linearLayout.visibility =
                View.VISIBLE
            else
                linearLayout.visibility = View.INVISIBLE
        }

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, url: String) {
            if (!url.isNullOrEmpty()) {
                imageView.load(url) {
                    crossfade(500)
                    error(R.drawable.baseline_restaurant_menu_24)
                }
            }
        }

        @BindingAdapter("numberOfLikes")
        @JvmStatic
        fun setNumbersOfLikes(textView: TextView, likes: Int) {
            textView.text = likes.toString()
        }

        @BindingAdapter("timeToCook")
        @JvmStatic
        fun setTimeToCook(textView: TextView, time: Int) {
            textView.text = time.toString()
        }

        @BindingAdapter("isVegan")
        @JvmStatic
        fun isVegan(view: View, isVegan: Boolean) {
            if (isVegan) {
                when (view) {
                    is TextView -> view.setTextColor(
                        ContextCompat.getColor(
                            view.context, R.color.green
                        )
                    )

                    is ImageView -> view.setColorFilter(
                        ContextCompat.getColor(
                            view.context, R.color.green
                        )
                    )
                }
            }
        }
    }
}