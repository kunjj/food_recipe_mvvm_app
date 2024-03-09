package com.example.foodrecipesapplication.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipesapplication.FoodRecipeApplication
import com.example.foodrecipesapplication.R
import com.example.foodrecipesapplication.models.FoodRecipe
import com.example.foodrecipesapplication.network.NetworkResponse
import com.example.foodrecipesapplication.repositories.Repository
import com.example.foodrecipesapplication.utils.Constant
import kotlinx.coroutines.launch
import retrofit2.Response

class FoodRecipesViewModel(private val repository: Repository) : ViewModel() {
    var foodRecipesResponse: MutableLiveData<NetworkResponse<FoodRecipe>> = MutableLiveData()

    fun getRandomRecipes(queries: Map<String, String>) = viewModelScope.launch {
        getSafeApiCall(queries)
    }

    private suspend fun getSafeApiCall(queries: Map<String, String>) {
        foodRecipesResponse.value = NetworkResponse.Loading()
        if (Constant.hasInternetConnection()) {
            val response = repository.getRandomRecipes(queries)
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