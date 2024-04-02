package com.example.foodrecipesapplication.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipesapplication.databinding.IngredientRowBinding
import com.example.foodrecipesapplication.models.ExtendedIngredient

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {
    class IngredientsViewHolder(val binding: IngredientRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ingredient: ExtendedIngredient) {
            binding.ingredient = ingredient
        }

        companion object {
            fun from(parent: ViewGroup): IngredientsViewHolder {
                val from = LayoutInflater.from(parent.context)
                val binding = IngredientRowBinding.inflate(from)
                return IngredientsViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder =
        IngredientsViewHolder.from(parent)

    private val ingredientsDifferCallback = object : DiffUtil.ItemCallback<ExtendedIngredient>() {
        override fun areItemsTheSame(
            oldItem: ExtendedIngredient, newItem: ExtendedIngredient
        ): Boolean = oldItem === newItem

        override fun areContentsTheSame(
            oldItem: ExtendedIngredient, newItem: ExtendedIngredient
        ): Boolean = oldItem == newItem
    }

    var ingredients = AsyncListDiffer(this, ingredientsDifferCallback)

    override fun getItemCount(): Int {
        Log.d("vcsd","size: "+this.ingredients.currentList.size.toString())
        return this.ingredients.currentList.size
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        holder.bind(this.ingredients.currentList[position])
    }
}