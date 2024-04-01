package com.example.foodrecipesapplication.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodRecipe(
    @SerializedName("results")
    val recipes: List<Recipe>
) : Parcelable