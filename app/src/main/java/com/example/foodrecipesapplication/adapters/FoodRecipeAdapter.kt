package com.example.foodrecipesapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipesapplication.databinding.RecipeRowBinding
import com.example.foodrecipesapplication.models.Recipe

class FoodRecipeAdapter : RecyclerView.Adapter<FoodRecipeAdapter.RecipeViewHolder>() {
    class RecipeViewHolder(private val binding: RecipeRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Recipe) {
            binding.recipe = recipe
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): RecipeViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecipeRowBinding.inflate(layoutInflater)
                return RecipeViewHolder(binding)
            }
        }
    }

    private val recipesDifferCallback = object : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
            oldItem == newItem
    }

    var recipes = AsyncListDiffer(this, recipesDifferCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder =
        RecipeViewHolder.from(parent)

    override fun getItemCount(): Int = this.recipes.currentList.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(this.recipes.currentList[position])
    }
}