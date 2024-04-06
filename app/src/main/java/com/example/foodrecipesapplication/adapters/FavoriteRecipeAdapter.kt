package com.example.foodrecipesapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipesapplication.databinding.FavoriteRecipeRowBinding

class FavoriteRecipeAdapter : RecyclerView.Adapter<FavoriteRecipeAdapter.RecipeViewHolder>() {
    class RecipeViewHolder(val binding: FavoriteRecipeRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): RecipeViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavoriteRecipeRowBinding.inflate(layoutInflater)
                return RecipeViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder.from(parent)
    }

    override fun getItemCount(): Int = 5

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {

    }
}