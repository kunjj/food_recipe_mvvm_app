package com.example.foodrecipesapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.foodrecipesapplication.R
import com.example.foodrecipesapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        window.statusBarColor = ResourcesCompat.getColor(resources, R.color.black_overlay, null)
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