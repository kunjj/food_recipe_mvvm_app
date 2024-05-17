package com.example.foodrecipesapplication.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodrecipesapplication.room.converters.FoodJokeConverter
import com.example.foodrecipesapplication.room.converters.RecipesTypeConverter
import com.example.foodrecipesapplication.room.dao.RecipesDao
import com.example.foodrecipesapplication.room.entities.FavoriteRecipe
import com.example.foodrecipesapplication.room.entities.FoodJokeEntity
import com.example.foodrecipesapplication.room.entities.FoodRecipeEntity
import java.io.Serializable

@Database(
    entities = [FoodRecipeEntity::class, FavoriteRecipe::class, FoodJokeEntity::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(RecipesTypeConverter::class, FoodJokeConverter::class)
abstract class RecipeDatabase : RoomDatabase(), Serializable {
    abstract fun recipesDao(): RecipesDao
}