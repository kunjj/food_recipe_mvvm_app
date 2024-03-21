package com.example.foodrecipesapplication.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.foodrecipesapplication.R
import com.example.foodrecipesapplication.databinding.ActivityMainBinding
import com.example.foodrecipesapplication.network.NetworkListener
import com.example.foodrecipesapplication.utils.DataStoreHelper
import com.example.foodrecipesapplication.viewmodelfactory.RecipeViewModelFactory
import com.example.foodrecipesapplication.viewmodels.FoodRecipesViewModel
import com.example.foodrecipesapplication.viewmodels.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    val foodRecipesViewModel: FoodRecipesViewModel by viewModels()
    lateinit var recipeViewModel: RecipeViewModel
    val networkListener: NetworkListener by lazy { NetworkListener() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        window.statusBarColor = ResourcesCompat.getColor(resources, R.color.black_overlay, null)
        val recipeViewModelFactory = RecipeViewModelFactory(DataStoreHelper(this))
        this.recipeViewModel =
            ViewModelProvider(this, recipeViewModelFactory)[RecipeViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()
//        binding.bottomNavigationView.setupWithNavController(binding.newsNavHostFragment.findNavController())
        navController = Navigation.findNavController(this, R.id.navHostFragment)

        binding.bottomNavigationView.addBubbleListener { id ->
            when (id) {
                R.id.recipeFragment -> navController.navigate(R.id.recipeFragment)
                R.id.favoriteRecipeFragment -> navController.navigate(R.id.favoriteRecipeFragment)
                R.id.foodJokesFragment -> navController.navigate(R.id.foodJokesFragment)
            }
        }
    }
}