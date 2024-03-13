package com.example.foodrecipesapplication.models


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodrecipesapplication.utils.Constant
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodRecipe(
    @SerializedName("recipes")
    val recipes: List<Recipe>
) : Parcelable