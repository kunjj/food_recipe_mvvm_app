package com.example.foodrecipesapplication.repositories

import com.example.foodrecipesapplication.models.FoodRecipe
import com.example.foodrecipesapplication.network.FoodRecipeAPI
import com.example.foodrecipesapplication.room.dao.RecipesDao
import com.example.foodrecipesapplication.room.entities.FoodRecipeEntity
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class FoodRecipesRepository @Inject constructor(
    private val foodRecipeAPI: FoodRecipeAPI,
    private val recipesDao: RecipesDao
) {
    //Remote DataSource.
    suspend fun getRandomRecipes(queries: Map<String, String>): Response<FoodRecipe> =
        foodRecipeAPI.getRandomFoodRecipes(queries)

    // Room Database queries.
    suspend fun insertFoodRecipes(foodRecipe: FoodRecipeEntity) =
        recipesDao.insertRecipes(foodRecipe)

    fun readDatabase(): Flow<List<FoodRecipeEntity>> = this.recipesDao.readRecipes()
}