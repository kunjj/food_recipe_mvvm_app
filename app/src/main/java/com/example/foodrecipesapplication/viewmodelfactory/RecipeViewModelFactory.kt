package com.example.foodrecipesapplication.viewmodelfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.foodrecipesapplication.utils.DataStoreHelper
import com.example.foodrecipesapplication.viewmodels.RecipeViewModel

class RecipeViewModelFactory(
    private val context: Context,
    private val dataStoreHelper: DataStoreHelper,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        RecipeViewModel(context, dataStoreHelper) as T
}