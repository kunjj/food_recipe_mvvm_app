package com.example.foodrecipesapplication.adapters

import android.view.ActionMode
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipesapplication.R
import com.example.foodrecipesapplication.databinding.FavoriteRecipeRowBinding
import com.example.foodrecipesapplication.room.entities.FavoriteRecipe

class FavoriteRecipeAdapter(private val activity: FragmentActivity) :
    RecyclerView.Adapter<FavoriteRecipeAdapter.RecipeViewHolder>(),
    ActionMode.Callback {
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

    private val favoriteRecipesDifferCallback = object : DiffUtil.ItemCallback<FavoriteRecipe>() {
        override fun areItemsTheSame(oldItem: FavoriteRecipe, newItem: FavoriteRecipe): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: FavoriteRecipe, newItem: FavoriteRecipe): Boolean =
            oldItem.id == newItem.id
    }

    var favoriteRecipes = AsyncListDiffer(this, favoriteRecipesDifferCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder.from(parent)
    }

    override fun getItemCount(): Int = this.favoriteRecipes.currentList.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.binding.recipe = favoriteRecipes.currentList[position].recipe
        holder.binding.executePendingBindings()

        holder.binding.clRecipe.setOnLongClickListener {
            activity.startActionMode(this)
            true
        }
    }

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        mode!!.menuInflater.inflate(R.menu.favorite_recipe_menu, menu)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        return true
    }

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        return true
    }

    override fun onDestroyActionMode(mode: ActionMode?) {

    }
}