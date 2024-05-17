package com.example.foodrecipesapplication.room.converters

import androidx.room.TypeConverter
import com.example.foodrecipesapplication.models.FoodJoke
import com.google.gson.Gson

class FoodJokeConverter {
    @TypeConverter
    fun foodJokeToString(foodJoke: FoodJoke): String = Gson().toJson(foodJoke)

    @TypeConverter
    fun stringToFoodJoke(data: String): FoodJoke = Gson().fromJson(data, FoodJoke::class.java)
}