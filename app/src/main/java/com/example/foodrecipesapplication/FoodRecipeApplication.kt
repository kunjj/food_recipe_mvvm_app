package com.example.foodrecipesapplication

import android.app.Application

class FoodRecipeApplication : Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: FoodRecipeApplication

        fun getApplicationContext() = this.instance.applicationContext
    }
}