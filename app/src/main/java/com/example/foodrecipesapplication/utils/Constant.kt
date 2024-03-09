package com.example.foodrecipesapplication.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.foodrecipesapplication.FoodRecipeApplication

object Constant {
    const val API_KEY = "58674c4257de455d96e42732051dbc9b"

    const val BASE_URL = "https://api.spoonacular.com"

    fun hasInternetConnection(): Boolean {
        val connectivityManager = FoodRecipeApplication.getApplicationContext()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false

        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}