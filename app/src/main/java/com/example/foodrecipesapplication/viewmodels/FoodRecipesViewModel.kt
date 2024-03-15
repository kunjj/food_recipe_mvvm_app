package com.example.foodrecipesapplication.viewmodels

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodrecipesapplication.R
import com.example.foodrecipesapplication.models.FoodRecipe
import com.example.foodrecipesapplication.network.NetworkResponse
import com.example.foodrecipesapplication.repositories.FoodRecipesRepository
import com.example.foodrecipesapplication.room.entities.FoodRecipeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class FoodRecipesViewModel(
    private val context: Context,
    private val foodRecipesRepository: FoodRecipesRepository
) : ViewModel() {
    var foodRecipesResponse: MutableLiveData<NetworkResponse<FoodRecipe>> = MutableLiveData()

    val readRecipes: LiveData<List<FoodRecipeEntity>> =
        foodRecipesRepository.readDatabase().asLiveData()

    private fun insertRecipesToRoomDatabase(foodRecipeEntity: FoodRecipeEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            foodRecipesRepository.insertFoodRecipes(foodRecipeEntity)
        }

    fun getRandomRecipes(queries: Map<String, String>) = viewModelScope.launch {
        getSafeApiCall(queries)
    }

    private suspend fun getSafeApiCall(queries: Map<String, String>) {
        foodRecipesResponse.value = NetworkResponse.Loading()
        if (hasInternetConnection()) {
            val response = foodRecipesRepository.getRandomRecipes(queries)
            foodRecipesResponse.value = handleResponse(response)
            val foodRecipes = foodRecipesResponse.value!!.data
            if (foodRecipes != null) offlineCacheRecipes(foodRecipes)
        } else foodRecipesResponse.value = NetworkResponse.Error(
            context.getString(
                R.string.not_connected_to_internet
            )
        )
    }

    private fun offlineCacheRecipes(foodRecipes: FoodRecipe) =
        insertRecipesToRoomDatabase(FoodRecipeEntity(foodRecipe = foodRecipes))

    private fun handleResponse(response: Response<FoodRecipe>): NetworkResponse<FoodRecipe> {
        return when {
            response.message().contains("timeout") -> NetworkResponse.Error(
                context.getString(R.string.connection_timeout)
            )

            response.code() == 402 -> NetworkResponse.Error(
                context.getString(R.string.try_after_sometimes)
            )

            response.body()!!.recipes.isNullOrEmpty() -> NetworkResponse.Error(
                context.applicationContext.getString(R.string.no_recipes_found)
            )

            response.isSuccessful -> NetworkResponse.Success(response.body()!!)

            else -> NetworkResponse.Error(response.message())
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

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