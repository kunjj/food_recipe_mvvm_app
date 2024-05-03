package com.example.foodrecipesapplication.adapters

import android.view.ActionMode
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipesapplication.R
import com.example.foodrecipesapplication.databinding.FavoriteRecipeRowBinding
import com.example.foodrecipesapplication.models.Recipe
import com.example.foodrecipesapplication.room.entities.FavoriteRecipe
import com.example.foodrecipesapplication.ui.fragments.FavoriteRecipeFragmentDirections
import com.example.foodrecipesapplication.viewmodels.FoodRecipesViewModel

class FavoriteRecipeAdapter(
    private val activity: FragmentActivity,
    private val viewModel: FoodRecipesViewModel,
) : RecyclerView.Adapter<FavoriteRecipeAdapter.RecipeViewHolder>(), ActionMode.Callback {
    private lateinit var actionMode: ActionMode

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
    private val selectedItems = mutableSetOf<Recipe>()
    private var multiSelection = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder.from(parent)
    }

    override fun getItemCount(): Int = this.favoriteRecipes.currentList.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = this.favoriteRecipes.currentList[position].recipe
        holder.binding.recipe = recipe
        holder.binding.executePendingBindings()

        //For Long Click on Recipe.
        holder.binding.clRecipe.setOnLongClickListener {
            if (!multiSelection) {
                multiSelection = true
                activity.startActionMode(this)
                toggleSelection(holder, recipe)
                true
            } else {
                multiSelection = false
                false
            }
        }

        //For Click on Recipe.
        holder.binding.clRecipe.setOnClickListener {
            if (multiSelection) toggleSelection(holder, recipe)
            else {
                val action =
                    FavoriteRecipeFragmentDirections.actionFavoriteRecipeFragmentToDetailsActivity(
                        recipe
                    )
                holder.binding.clRecipe.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        mode?.menuInflater?.inflate(R.menu.favorite_recipe_menu, menu)
        actionMode = mode!!
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?) = true

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.deleteFavoriteRecipe -> {
                this.selectedItems.forEach { recipe ->
                    val favoriteRecipe = FavoriteRecipe(recipe.id, recipe)
                    viewModel.deleteFavoriteRecipe(favoriteRecipe)
                }
                clearSelection()
            }
        }
        return true
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        clearSelection()
    }

    private fun toggleSelection(holder: RecipeViewHolder, recipe: Recipe) = apply {
        if (selectedItems.contains(recipe)) {
            selectedItems.remove(recipe)
            changeSelectedRecipeStyle(holder, R.color.white)
            applyActionModeTitle()
        } else {
            selectedItems.add(recipe)
            changeSelectedRecipeStyle(holder, R.color.green)
            applyActionModeTitle()
        }
    }

    private fun changeSelectedRecipeStyle(holder: RecipeViewHolder, strokeColor: Int) =
        holder.binding.apply {
            cvRecipe.strokeColor = ContextCompat.getColor(activity, strokeColor)
        }

    private fun applyActionModeTitle() = apply {
        when (selectedItems.size) {
            0 -> clearSelection()

            1 -> actionMode.title = "${selectedItems.size} item selected"

            else -> actionMode.title = "${selectedItems.size} items selected"
        }
    }

    fun clearSelection() {
        selectedItems.clear()
        if (this::actionMode.isInitialized) actionMode.finish()
        multiSelection = false
    }
}