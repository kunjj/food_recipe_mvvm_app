package com.example.foodrecipesapplication.repositories

import com.example.foodrecipesapplication.models.FoodRecipe
import com.example.foodrecipesapplication.network.FoodRecipeAPI
import com.example.foodrecipesapplication.room.dao.RecipesDao
import com.example.foodrecipesapplication.room.entities.FavoriteRecipe
import com.example.foodrecipesapplication.room.entities.FoodRecipeEntity
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class FoodRecipesRepository @Inject constructor(
    private val foodRecipeAPI: FoodRecipeAPI, private val recipesDao: RecipesDao,
) {
    // Room Database queries.
    suspend fun insertFoodRecipes(foodRecipe: FoodRecipeEntity) =
        recipesDao.insertRecipes(foodRecipe)

    suspend fun insertFavoriteRecipes(favoriteRecipe: FavoriteRecipe) =
        recipesDao.insertFavoriteRecipe(favoriteRecipe)

    fun readDatabase(): Flow<List<FoodRecipeEntity>> = this.recipesDao.readRecipes()

    fun readFavoriteRecipes(): Flow<List<FavoriteRecipe>> = this.recipesDao.readFavoriteRecipes()

    suspend fun deleteFavoriteRecipe(favoriteRecipe: FavoriteRecipe) =
        this.recipesDao.deleteFavoriteRecipe(favoriteRecipe)

    suspend fun deleteAllFavoriteRecipes() = this.recipesDao.deleteAllFavoriteRecipe()

    //Remote DataSource.
    suspend fun getRandomRecipes(queries: Map<String, String>): Response<FoodRecipe> =
        foodRecipeAPI.getRandomFoodRecipes(queries)

    suspend fun searchRecipes(searchQuery: Map<String, String>): Response<FoodRecipe> =
        foodRecipeAPI.getSearchedFoodRecipes(searchQuery)
}