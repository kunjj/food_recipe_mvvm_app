package com.example.foodrecipesapplication.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipesapplication.models.FoodRecipe
import com.example.foodrecipesapplication.network.NetworkResponse
import com.example.foodrecipesapplication.repositories.Repository
import kotlinx.coroutines.launch

class FoodRecipesViewModel(private val repository: Repository) : ViewModel() {
    var foodRecipesResponse: MutableLiveData<NetworkResponse<FoodRecipe>> = MutableLiveData()

    fun getRandomRecipes(queries: Map<String, String>) = viewModelScope.launch {
        val response = repository.getRandomRecipes(queries)
    }
}