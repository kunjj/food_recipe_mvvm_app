package com.example.foodrecipesapplication.adapters

import android.content.Context
import android.util.Log
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
        with(recipe){
            if (vegan && position == 0){
                holder.binding.tvOption.setTextColor(ContextCompat.getColor(context,R.color.green))
                holder.binding.ivChecked.setColorFilter(ContextCompat.getColor(context,R.color.green))
            }

            if(glutenFree && position == 1){
                holder.binding.tvOption.setTextColor(ContextCompat.getColor(context,R.color.green))
                holder.binding.ivChecked.setColorFilter(ContextCompat.getColor(context,R.color.green))
            }

            if(vegetarian && position == 2){
                holder.binding.tvOption.setTextColor(ContextCompat.getColor(context,R.color.green))
                holder.binding.ivChecked.setColorFilter(ContextCompat.getColor(context,R.color.green))
            }

            if(dairyFree && position == 3){
                holder.binding.tvOption.setTextColor(ContextCompat.getColor(context,R.color.green))
                holder.binding.ivChecked.setColorFilter(ContextCompat.getColor(context,R.color.green))
            }

            if(cheap && position == 4){
                holder.binding.tvOption.setTextColor(ContextCompat.getColor(context,R.color.green))
                holder.binding.ivChecked.setColorFilter(ContextCompat.getColor(context,R.color.green))
            }

            if(veryHealthy && position == 5){
                holder.binding.tvOption.setTextColor(ContextCompat.getColor(context,R.color.green))
                holder.binding.ivChecked.setColorFilter(ContextCompat.getColor(context,R.color.green))
            }
        }
    }
}