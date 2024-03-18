package com.example.foodrecipesapplication.utils

object Constant {
    const val API_KEY = "58674c4257de455d96e42732051dbc9b"
    const val BASE_URL = "https://api.spoonacular.com"

    //Room Database.
    const val DATABASE_NAME = "recipes_database"
    const val TABLE_NAME = "recipes_table"

    //API Queries Key.
    const val QUERY_API_KEY = "apiKey"
    const val QUERY_PAGE_NUMBER = "number"
    const val QUERY_MEAL_TYPE = "type"
    const val QUERY_DIET_TYPE = "diet"
    const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
    const val FILL_INGREDIENTS = "fillIngredients"
    const val QUERY_INSTRUCTION_REQUIRED = "instructionsRequired"

    // For Bottom Sheet.
    const val DEFAULT_PAGE_NUMBER = "100"
    const val DEFAULT_MEAL_TYPE = "main course"
    const val DEFAULT_DIET_TYPE = "gluten free"
}