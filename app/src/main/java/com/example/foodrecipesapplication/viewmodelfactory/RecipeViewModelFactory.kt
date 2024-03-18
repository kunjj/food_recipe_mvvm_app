package com.example.foodrecipesapplication.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.foodrecipesapplication.utils.DataStoreHelper
import com.example.foodrecipesapplication.viewmodels.RecipeViewModel

class RecipeViewModelFactory(
    private val dataStoreHelper: DataStoreHelper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecipeViewModel(dataStoreHelper) as T
    }
}