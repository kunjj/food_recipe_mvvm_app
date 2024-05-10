package com.example.foodrecipesapplication.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

object Constant {
    const val API_KEY = "9fcf55dbef7a41859b3ecf56e7712abc"
    const val BASE_IMAGE_URL = "https://img.spoonacular.com/ingredients_100x100/"
    const val BASE_URL = "https://api.spoonacular.com"

    //Room Database.
    const val DATABASE_NAME = "recipes_database"
    const val TABLE_NAME = "recipes_table"
    const val FAVORITE_RECIPE_TABLE = "favorite_recipes"
    const val FOOD_JOKE_TABLE = "food_joke"

    //API Queries Key.
    const val QUERY_API_KEY = "apiKey"
    const val QUERY_PAGE_NUMBER = "number"
    const val QUERY_MEAL_TYPE = "type"
    const val QUERY_DIET_TYPE = "diet"
    const val QUERY_CUISINE = "cuisine"
    const val QUERY_SEARCH = "query"
    const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
    const val QUERY_ADD_RECIPE_INSTRUCTION = "addRecipeInstructions"
    const val FILL_INGREDIENTS = "fillIngredients"
    const val QUERY_INSTRUCTION_REQUIRED = "instructionsRequired"

    // For Bottom Sheet.
    const val DEFAULT_PAGE_NUMBER = "100"
    const val DEFAULT_MEAL_TYPE = "main course"
    const val DEFAULT_DIET_TYPE = "gluten free"

    //For DataStore Preference.
    const val RECIPE_TYPE = "Food Recipe"
    const val MEAL_TYPE = "mealType"
    const val MEAL_TYPE_ID = "mealTypeId"
    const val DIET_TYPE = "dietType"
    const val DIET_TYPE_ID = "dietTypeId"

    fun showSnackBar(view: View, message: String) = apply {
        Snackbar.make(view,message, Snackbar.LENGTH_SHORT).show()
    }
}