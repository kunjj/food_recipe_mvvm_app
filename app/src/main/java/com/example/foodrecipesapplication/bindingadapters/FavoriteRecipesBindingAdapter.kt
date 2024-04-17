package com.example.foodrecipesapplication.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipesapplication.room.entities.FavoriteRecipe

class FavoriteRecipesBindingAdapter {
    companion object {
        @BindingAdapter("setDataVisibility")
        @JvmStatic
        fun setDataVisibility(view: View, favoriteRecipes: List<FavoriteRecipe>?) {
            favoriteRecipes?.let {
                if (it.isEmpty()) {
                    when (view) {
                        is ImageView -> view.visibility = View.VISIBLE

                        is TextView -> view.visibility = View.VISIBLE

                        is RecyclerView -> view.visibility = View.GONE
                    }
                } else {
                    when (view) {
                        is ImageView -> view.visibility = View.GONE

                        is TextView -> view.visibility = View.GONE

                        is RecyclerView -> view.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}