package com.example.foodrecipesapplication.network

import com.example.foodrecipesapplication.models.FoodRecipe
import com.example.foodrecipesapplication.utils.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitInstance {

    private val retrofit by lazy {
        val client = OkHttpClient.Builder().build()
        Retrofit.Builder().baseUrl(Constant.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    val api by lazy { retrofit.create(FoodRecipeAPI::class.java) }
}