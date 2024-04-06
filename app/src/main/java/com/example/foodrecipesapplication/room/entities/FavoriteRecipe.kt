package com.example.foodrecipesapplication.room.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodrecipesapplication.models.Recipe
import com.example.foodrecipesapplication.utils.Constant
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = Constant.FAVORITE_RECIPE_TABLE)
data class FavoriteRecipe(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val recipe: Recipe
) : Parcelable