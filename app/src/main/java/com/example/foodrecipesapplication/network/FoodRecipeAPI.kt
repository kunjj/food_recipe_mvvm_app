package com.example.foodrecipesapplication.network

import com.example.foodrecipesapplication.models.FoodJoke
import com.example.foodrecipesapplication.models.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface FoodRecipeAPI {
    @GET("/recipes/complexSearch?")
    suspend fun getRandomFoodRecipes(@QueryMap queries: Map<String, String>): Response<FoodRecipe>

    @GET("/recipes/complexSearch?")
    suspend fun getSearchedFoodRecipes(@QueryMap searchQuery: Map<String, String>): Response<FoodRecipe>

    @GET("/food/jokes/random?")
    suspend fun getRandomFoodJoke(
        @Query("apiKey") apiKey: String
    ): Response<FoodJoke>

}