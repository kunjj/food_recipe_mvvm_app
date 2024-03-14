package com.example.foodrecipesapplication.repositories

import com.example.foodrecipesapplication.network.NetworkModule
import com.example.foodrecipesapplication.room.dao.RecipesDao
import com.example.foodrecipesapplication.room.entities.FoodRecipeEntity
import kotlinx.coroutines.flow.Flow

class FoodRecipesRepository(private val recipesDao: RecipesDao) {
    suspend fun getRandomRecipes(queries: Map<String, String>) =
        NetworkModule.apiServices.getRandomFoodRecipes(queries)

    // Room Database queries.
    suspend fun insertFoodRecipes(foodRecipe: FoodRecipeEntity) =
        recipesDao.insertRecipes(foodRecipe)

    fun readDatabase(): Flow<List<FoodRecipeEntity>> = this.recipesDao.readRecipes()
}