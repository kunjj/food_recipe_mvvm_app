package com.example.foodrecipesapplication.repositories

import com.example.foodrecipesapplication.network.NetworkModule

class FoodRecipesRepository {
    suspend fun getRandomRecipes(queries: Map<String,String>) = NetworkModule.apiServices.getRandomFoodRecipes(queries)
}