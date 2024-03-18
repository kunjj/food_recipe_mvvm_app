package com.example.foodrecipesapplication.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipesapplication.utils.Constant
import com.example.foodrecipesapplication.utils.DataStoreHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeViewModel(
    private val dataStoreHelper: DataStoreHelper
) : ViewModel() {
    private var mealType = Constant.DEFAULT_MEAL_TYPE
    private var dietType = Constant.DEFAULT_DIET_TYPE

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

    fun queries(): HashMap<String, String> {
        val queries = HashMap<String, String>()
        queries[Constant.QUERY_API_KEY] = Constant.API_KEY
        queries[Constant.QUERY_PAGE_NUMBER] = Constant.DEFAULT_PAGE_NUMBER
        queries[Constant.QUERY_MEAL_TYPE] = this.mealType
        queries[Constant.QUERY_DIET_TYPE] = this.dietType
        queries[Constant.QUERY_ADD_RECIPE_INFORMATION] = "true"
        return queries
    }
}