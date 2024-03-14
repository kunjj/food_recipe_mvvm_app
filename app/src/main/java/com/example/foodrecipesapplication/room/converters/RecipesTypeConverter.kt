package com.example.foodrecipesapplication.room.converters

import androidx.room.TypeConverter
import com.example.foodrecipesapplication.models.FoodRecipe
import com.example.foodrecipesapplication.models.Recipe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipesTypeConverter {
    @TypeConverter
    fun foodRecipeToString(foodRecipe: FoodRecipe): String = Gson().toJson(foodRecipe)

    @TypeConverter
    fun stringToFoodRecipe(data: String): FoodRecipe {
//        val listType = object : TypeToken<FoodRecipe>() {}.type
        return Gson().fromJson(data, FoodRecipe::class.java)
    }

    @TypeConverter
    fun recipeToString(foodRecipe: List<Recipe>): String = Gson().toJson(foodRecipe)

    @TypeConverter
    fun stringToRecipe(data: String): List<Recipe> {
        val listType = object : TypeToken<FoodRecipe>() {}.type
        return Gson().fromJson(data, listType)
    }
}