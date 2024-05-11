package com.example.foodrecipesapplication.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by Kunjan on 10-05-2024.
 */
@Parcelize
data class FoodJoke(
    @SerializedName("text")
    val text: String
) : Parcelable