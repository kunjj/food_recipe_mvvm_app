package com.example.foodrecipesapplication.network

import com.example.foodrecipesapplication.models.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface FoodRecipeAPI {

    @GET("/recipes/random")
    suspend fun getRandomFoodRecipes(@QueryMap queries: Map<String,String>): Response<FoodRecipe>
}