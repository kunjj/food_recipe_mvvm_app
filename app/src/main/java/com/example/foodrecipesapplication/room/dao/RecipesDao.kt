package com.example.foodrecipesapplication.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodrecipesapplication.room.entities.FavoriteRecipe
import com.example.foodrecipesapplication.room.entities.FoodJokeEntity
import com.example.foodrecipesapplication.room.entities.FoodRecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(foodRecipe: FoodRecipeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(recipe: FavoriteRecipe)

    @Query("SELECT * FROM recipes_table")
    fun readRecipes(): Flow<List<FoodRecipeEntity>>

    @Query("SELECT * FROM favorite_recipes")
    fun readFavoriteRecipes(): Flow<List<FavoriteRecipe>>

    @Delete
    suspend fun deleteFavoriteRecipe(favoriteRecipe: FavoriteRecipe)

    @Query("DELETE FROM favorite_recipes")
    suspend fun deleteAllFavoriteRecipe()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFoodJoke(foodJoke: FoodJokeEntity)

    @Query("SELECT * FROM food_joke")
    fun readFoodJoke(): Flow<FoodJokeEntity>
}