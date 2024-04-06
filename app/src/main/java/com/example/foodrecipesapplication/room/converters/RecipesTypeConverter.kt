package com.example.foodrecipesapplication.room.converters

import androidx.room.TypeConverter
import com.example.foodrecipesapplication.models.FoodRecipe
import com.example.foodrecipesapplication.models.Recipe
import com.google.gson.Gson

class RecipesTypeConverter {
    @TypeConverter
    fun foodRecipeToString(foodRecipe: FoodRecipe): String = Gson().toJson(foodRecipe)

    @TypeConverter
    fun stringToFoodRecipe(data: String): FoodRecipe = Gson().fromJson(data, FoodRecipe::class.java)

    @TypeConverter
    fun recipeToString(foodRecipe: Recipe): String = Gson().toJson(foodRecipe)

    @TypeConverter
    fun stringToRecipe(data: String): Recipe = Gson().fromJson(data, Recipe::class.java)
}