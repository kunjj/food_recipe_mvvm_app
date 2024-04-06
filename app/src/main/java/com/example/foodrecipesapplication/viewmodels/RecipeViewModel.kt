package com.example.foodrecipesapplication.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipesapplication.utils.Constant
import com.example.foodrecipesapplication.utils.DataStoreHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeViewModel(
    private val context: Context,
    private val dataStoreHelper: DataStoreHelper
) : ViewModel() {
    private var mealType = Constant.DEFAULT_MEAL_TYPE
    private var dietType = Constant.DEFAULT_DIET_TYPE
    var isConnectedToInternet = false

    val readMealAndType = dataStoreHelper.readMealAndDietType.also {
        viewModelScope.launch(Dispatchers.IO) {
            it.collect { values ->
                this@RecipeViewModel.mealType = values.mealType
                this@RecipeViewModel.dietType = values.dietType
            }
        }
    }

    fun saveMealAndType(mealType: String, mealTypeId: Int, dietType: String, dietTypeId: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreHelper.saveMealAndDietType(mealType, mealTypeId, dietType, dietTypeId)
        }

    fun queries(searchQuery: String = ""): HashMap<String, String> {
        val queries = HashMap<String, String>()
        queries[Constant.QUERY_API_KEY] = Constant.API_KEY
        queries[Constant.QUERY_SEARCH] = searchQuery
        queries[Constant.QUERY_PAGE_NUMBER] = Constant.DEFAULT_PAGE_NUMBER
        queries[Constant.QUERY_MEAL_TYPE] = this.mealType
//        queries[Constant.QUERY_CUISINE] = "indian"
        queries[Constant.QUERY_DIET_TYPE] = this.dietType
        queries[Constant.QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[Constant.FILL_INGREDIENTS] = "true"
        queries[Constant.QUERY_INSTRUCTION_REQUIRED] = "true"
        queries[Constant.QUERY_ADD_RECIPE_INSTRUCTION] = "true"
        return queries
    }
}