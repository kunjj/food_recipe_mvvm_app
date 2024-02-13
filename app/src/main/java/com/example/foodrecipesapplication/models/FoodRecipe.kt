package com.example.foodrecipesapplication.models


import com.google.gson.annotations.SerializedName

data class FoodRecipe(
    @SerializedName("recipes")
    val recipes: List<Recipe>
)