package com.example.foodrecipesapplication.viewmodels

import android.app.Activity
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipesapplication.FoodRecipeApplication
import com.example.foodrecipesapplication.R
import com.example.foodrecipesapplication.models.FoodRecipe
import com.example.foodrecipesapplication.network.NetworkResponse
import com.example.foodrecipesapplication.repositories.FoodRecipesRepository
import com.example.foodrecipesapplication.utils.Constant
import kotlinx.coroutines.launch
import retrofit2.Response

class FoodRecipesViewModel(private val activity: Activity, private val foodRecipesRepository: FoodRecipesRepository) : ViewModel() {
    var foodRecipesResponse: MutableLiveData<NetworkResponse<FoodRecipe>> = MutableLiveData()

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false

        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    fun getRandomRecipes(queries: Map<String, String>) = viewModelScope.launch {
        getSafeApiCall(queries)
    }

    private suspend fun getSafeApiCall(queries: Map<String, String>) {
        foodRecipesResponse.value = NetworkResponse.Loading()
        if (hasInternetConnection()) {
            val response = foodRecipesRepository.getRandomRecipes(queries)
            foodRecipesResponse.value = handleResponse(response)
        } else foodRecipesResponse.value = NetworkResponse.Error(
            FoodRecipeApplication.getApplicationContext().getString(
                R.string.not_connected_to_internet
            )
        )
    }

    private fun handleResponse(response: Response<FoodRecipe>): NetworkResponse<FoodRecipe>? {
        return when {
            response.message().contains("timeout") -> NetworkResponse.Error(
                FoodRecipeApplication.getApplicationContext().getString(R.string.connection_timeout)
            )

            response.code() == 402 -> NetworkResponse.Error(
                FoodRecipeApplication.getApplicationContext()
                    .getString(R.string.try_after_sometimes)
            )

            response.body()!!.recipes.isEmpty() -> NetworkResponse.Error(
                FoodRecipeApplication.getApplicationContext().getString(R.string.no_recipes_found)
            )

            response.isSuccessful -> NetworkResponse.Success(response.body()!!)

            else -> NetworkResponse.Error(response.message())
        }
    }

}