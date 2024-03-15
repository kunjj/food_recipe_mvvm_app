package com.example.foodrecipesapplication

import android.app.Application
import android.content.Context

class FoodRecipeApplication : Application() {

    companion object {
        fun getApplicationContext(): Context = FoodRecipeApplication().applicationContext
    }
}