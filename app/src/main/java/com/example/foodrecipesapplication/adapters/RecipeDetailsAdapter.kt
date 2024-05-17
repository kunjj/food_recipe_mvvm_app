package com.example.foodrecipesapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipesapplication.R
import com.example.foodrecipesapplication.databinding.ItemRecipeDetailsBinding
import com.example.foodrecipesapplication.models.Recipe

class RecipeDetailsAdapter(private val context: Context, private val recipe: Recipe) :
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
        with(recipe) {
            if (vegan && position == 0) changeStyle(holder)

            if (glutenFree && position == 1) changeStyle(holder)

            if (vegetarian && position == 2) changeStyle(holder)

            if (dairyFree && position == 3) changeStyle(holder)

            if (cheap && position == 4) changeStyle(holder)

            if (veryHealthy && position == 5) changeStyle(holder)
        }
    }

    private fun changeStyle(holder: RecipeDetailsViewHolder) {
        holder.binding.tvOption.setTextColor(ContextCompat.getColor(context, R.color.green))
        holder.binding.ivChecked.setColorFilter(
            ContextCompat.getColor(
                context, R.color.green
            )
        )
    }
}