package com.example.foodrecipesapplication.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodrecipesapplication.models.FoodRecipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(foodRecipe: FoodRecipe)

    @Query("SELECT * FROM recipes_table")
    fun readRecipes(): Flow<List<FoodRecipe>>
}