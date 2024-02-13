package com.example.foodrecipesapplication.network

import com.example.foodrecipesapplication.models.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodRecipeAPI {

    @GET("/recipes/random")
    suspend fun getRandomFoodRecipes(@Query("apiKey") apiKey: String): Response<FoodRecipe>
}