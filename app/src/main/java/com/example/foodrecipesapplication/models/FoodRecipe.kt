package com.example.foodrecipesapplication.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodrecipesapplication.utils.Constant
import com.google.gson.annotations.SerializedName

@Entity(tableName = Constant.TABLE_NAME)
data class FoodRecipe(
    @SerializedName("recipes")
    val recipes: List<Recipe>
) {
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0
}