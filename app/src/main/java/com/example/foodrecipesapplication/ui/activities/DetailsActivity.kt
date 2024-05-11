package com.example.foodrecipesapplication.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.navArgs
import com.example.foodrecipesapplication.R
import com.example.foodrecipesapplication.adapters.RecipeDetailsViewPagerAdapter
import com.example.foodrecipesapplication.databinding.ActivityDetailsBinding
import com.example.foodrecipesapplication.room.entities.FavoriteRecipe
import com.example.foodrecipesapplication.ui.fragments.IngredientsFragment
import com.example.foodrecipesapplication.ui.fragments.InstructionFragment
import com.example.foodrecipesapplication.ui.fragments.OverviewFragment
import com.example.foodrecipesapplication.viewmodels.FoodRecipesViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    private var binding: ActivityDetailsBinding? = null
    private val args by navArgs<DetailsActivityArgs>()
    private val foodRecipesViewModel: FoodRecipesViewModel by viewModels()
    private var isRecipeSaved = false
    private var recipeUrl = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        setSupportActionBar(binding!!.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.recipeUrl = this.args.recipe.sourceUrl
    }

    override fun onStart() {
        super.onStart()
        this.binding!!.viewPager.adapter = getRecipeDetailsViewPagerAdapter()
        this.binding!!.tablayout.setupWithViewPager(this.binding!!.viewPager)
    }

    private fun getRecipeDetailsViewPagerAdapter(): RecipeDetailsViewPagerAdapter {
        val fragments = listOf(OverviewFragment(), IngredientsFragment(), InstructionFragment())

        val title = listOf(
            getString(R.string.overview),
            getString(R.string.ingredients),
            getString(R.string.instruction)
        )

        val bundle = Bundle().also {
            it.putParcelable("recipe", args.recipe)
        }
        return RecipeDetailsViewPagerAdapter(bundle, fragments, title, supportFragmentManager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            R.id.bookmarkRecipe -> {
                if (this.isRecipeSaved) removeFromFavoriteRecipe(item)
                else saveToFavorites(item)
                true
            }

            R.id.shareRecipe -> {
                val shareIntent = Intent().apply{
                    this.action = Intent.ACTION_SEND
                    this.putExtra(Intent.EXTRA_TEXT,recipeUrl)
                    this.type = "text/plain"
                }
                startActivity(Intent.createChooser(shareIntent,"Share Recipe Via"))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun removeFromFavoriteRecipe(item: MenuItem) {
        this.foodRecipesViewModel.deleteFavoriteRecipe(FavoriteRecipe(args.recipe.id, args.recipe))
        this.isRecipeSaved = false
        changeIcon(item, isRecipeSaved)
        showSnackBar(binding!!.root, getString(R.string.recipe_removed))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_activity_menu, menu)
        val menuItem = menu?.findItem(R.id.bookmarkRecipe)!!
        checkIfRecipeIsAlreadySavedOrNot(menuItem)
        return true
    }

    private fun checkIfRecipeIsAlreadySavedOrNot(menuItem: MenuItem) {
        this.foodRecipesViewModel.favoriteRecipes.observe(this) { favoriteRecipes ->
            try {
                for (favoriteRecipe in favoriteRecipes) {
                    if (favoriteRecipe.recipe.id == args.recipe.id){
                        this.isRecipeSaved = true
                        changeIcon(
                            menuItem, this.isRecipeSaved
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("DetailsActivity", e.printStackTrace().toString())
            }
        }
    }

    private fun saveToFavorites(item: MenuItem) {
        this.foodRecipesViewModel.saveFavoriteRecipe(FavoriteRecipe(args.recipe.id, args.recipe))
        this.isRecipeSaved = true
        changeIcon(item, this.isRecipeSaved)
        showSnackBar(binding!!.root, getString(R.string.recipe_saved))
    }

    private fun changeIcon(item: MenuItem, isRecipeSaved: Boolean) {
        if (isRecipeSaved) item.icon =
            ContextCompat.getDrawable(this, R.drawable.ic_bookmark_filled)
        else item.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark_outlined)
    }

    private fun showSnackBar(view: View, message: String) = apply {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.binding = null
    }
}