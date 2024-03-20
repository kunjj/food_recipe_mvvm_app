package com.example.foodrecipesapplication.viewmodelfactory

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.foodrecipesapplication.repositories.FoodRecipesRepository
import com.example.foodrecipesapplication.utils.NetworkListener
import com.example.foodrecipesapplication.viewmodels.FoodRecipesViewModel

class FoodRecipeViewModelFactory(private val activity: Activity,private val foodRecipesRepository: FoodRecipesRepository,private val networkListener: NetworkListener) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FoodRecipesViewModel(activity,foodRecipesRepository,networkListener) as T
    }
}