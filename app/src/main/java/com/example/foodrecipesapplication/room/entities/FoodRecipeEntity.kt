package com.example.foodrecipesapplication.room.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodrecipesapplication.models.FoodRecipe
import com.example.foodrecipesapplication.utils.Constant
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = Constant.TABLE_NAME)
data class FoodRecipeEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val foodRecipe: FoodRecipe
): Parcelable