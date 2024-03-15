package com.example.foodrecipesapplication

import android.app.Application
import android.content.Context

class FoodRecipeApplication : Application() {

    companion object {
        private var instance: FoodRecipeApplication = FoodRecipeApplication()

        fun getApplicationContext(): Context = this.instance.applicationContext
    }
}