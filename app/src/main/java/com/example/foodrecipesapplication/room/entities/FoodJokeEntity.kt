package com.example.foodrecipesapplication.room.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodrecipesapplication.models.FoodJoke
import com.example.foodrecipesapplication.utils.Constant
import kotlinx.parcelize.Parcelize

/**
 * Created by Kunjan on 10-05-2024.
 */
@Entity(tableName = Constant.FOOD_JOKE_TABLE)
@Parcelize
data class FoodJokeEntity(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    val foodJoke: FoodJoke,
) : Parcelable