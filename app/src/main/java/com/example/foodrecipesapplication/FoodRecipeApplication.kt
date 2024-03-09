package com.example.foodrecipesapplication

import android.app.Application
import android.content.Context

class FoodRecipeApplication : Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: FoodRecipeApplication

        fun getApplicationContext(): Context = this.instance.applicationContext
    }
}