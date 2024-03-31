package com.example.foodrecipesapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipesapplication.R
import com.example.foodrecipesapplication.databinding.ItemRecipeDetailsBinding
import com.example.foodrecipesapplication.models.Recipe

class RecipeDetailsAdapter(context: Context, private val recipe: Recipe) :
    RecyclerView.Adapter<RecipeDetailsAdapter.RecipeDetailsViewHolder>() {
    private val details = listOf(
        context.getString(R.string.vegan),
        context.getString(R.string.gluten_free),
        context.getString(R.string.vegetarian),
        context.getString(R.string.dairy_free),
        context.getString(R.string.cheap),
        context.getString(R.string.healthy)
    )

    class RecipeDetailsViewHolder(val binding: ItemRecipeDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): RecipeDetailsViewHolder {
                val binding = ItemRecipeDetailsBinding.inflate(LayoutInflater.from(parent.context))
                return RecipeDetailsViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeDetailsViewHolder =
        RecipeDetailsViewHolder.from(parent)

    override fun getItemCount(): Int = this.details.size

    override fun onBindViewHolder(holder: RecipeDetailsViewHolder, position: Int) {
        holder.binding.tvOption.text = this.details[position]

    }
}