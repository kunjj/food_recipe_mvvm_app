package com.example.foodrecipesapplication.ui

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.foodrecipesapplication.FoodRecipeApplication
import com.example.foodrecipesapplication.R
import com.example.foodrecipesapplication.databinding.ActivityMainBinding
import com.example.foodrecipesapplication.repositories.FoodRecipesRepository
import com.example.foodrecipesapplication.viewmodelfactory.FoodRecipeViewModelFactory
import com.example.foodrecipesapplication.viewmodels.FoodRecipesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    lateinit var foodRecipesViewModel: FoodRecipesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        window.statusBarColor = ResourcesCompat.getColor(resources, R.color.black_overlay, null)
        val foodRecipesRepository = FoodRecipesRepository()
        val foodRecipeViewModelFactory = FoodRecipeViewModelFactory(this,foodRecipesRepository)
        this.foodRecipesViewModel =
            ViewModelProvider(this, foodRecipeViewModelFactory)[FoodRecipesViewModel::class.java]
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