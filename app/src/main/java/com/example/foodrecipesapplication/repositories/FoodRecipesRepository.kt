package com.example.foodrecipesapplication.repositories

import com.example.foodrecipesapplication.network.RetroFitInstance

class FoodRecipesRepository {
    suspend fun getRandomRecipes(queries: Map<String,String>) = RetroFitInstance.apiServices.getRandomFoodRecipes(queries)
}