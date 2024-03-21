package com.example.foodrecipesapplication.viewmodelfactory

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.foodrecipesapplication.repositories.FoodRecipesRepository
import com.example.foodrecipesapplication.network.NetworkListener
import com.example.foodrecipesapplication.viewmodels.FoodRecipesViewModel

class FoodRecipeViewModelFactory(private val activity: Activity,private val foodRecipesRepository: FoodRecipesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FoodRecipesViewModel(activity,foodRecipesRepository) as T
    }
}