package com.example.foodrecipesapplication.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.foodrecipesapplication.R
import com.example.foodrecipesapplication.databinding.ActivityRecipeBinding
import com.example.foodrecipesapplication.network.NetworkListener
import com.example.foodrecipesapplication.utils.DataStoreHelper
import com.example.foodrecipesapplication.viewmodelfactory.RecipeViewModelFactory
import com.example.foodrecipesapplication.viewmodels.FoodRecipesViewModel
import com.example.foodrecipesapplication.viewmodels.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipeActivity : AppCompatActivity() {
    private var binding: ActivityRecipeBinding? = null
    private lateinit var navController: NavController
    val foodRecipesViewModel: FoodRecipesViewModel by viewModels()
    lateinit var recipeViewModel: RecipeViewModel
    private val networkListener: NetworkListener by lazy { NetworkListener() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val recipeViewModelFactory = RecipeViewModelFactory(this, DataStoreHelper(this))
        this.recipeViewModel =
            ViewModelProvider(this, recipeViewModelFactory)[RecipeViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()
//        binding.bottomNavigationView.setupWithNavController(binding.newsNavHostFragment.findNavController())
        navController = Navigation.findNavController(this, R.id.navHostFragment)

        binding!!.bottomNavigationView.addBubbleListener { id ->
            when (id) {
                R.id.recipeFragment -> navController.navigate(R.id.recipeFragment)
                R.id.favoriteRecipeFragment -> navController.navigate(R.id.favoriteRecipeFragment)
                R.id.foodJokesFragment -> navController.navigate(R.id.foodJokesFragment)
            }
        }

        lifecycleScope.launch {
            this@RecipeActivity.networkListener.isConnectedToInternet(this@RecipeActivity)
                .collect { status ->
                    recipeViewModel.isConnectedToInternet = status
                }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.binding = null
    }
}