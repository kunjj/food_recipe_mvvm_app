package com.example.foodrecipesapplication.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreHelper(val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constant.RECIPE_TYPE)

    object PreferencesKeys {
        val mealType = stringPreferencesKey(Constant.MEAL_TYPE)
        val mealTypeId = intPreferencesKey(Constant.MEAL_TYPE_ID)
        val dietType = stringPreferencesKey(Constant.DIET_TYPE)
        val dietTypeId = intPreferencesKey(Constant.DIET_TYPE_ID)
    }

    suspend fun saveMealAndDietType(
        mealType: String, mealTypeId: Int, dietType: String, dietTypeId: Int,
    ) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.mealType] = mealType
            preferences[PreferencesKeys.mealTypeId] = mealTypeId
            preferences[PreferencesKeys.dietType] = dietType
            preferences[PreferencesKeys.dietTypeId] = dietTypeId
        }
    }

    val readMealAndDietType: Flow<MealAndDietType> = context.dataStore.data.catch { exception ->
        if (exception is IOException) emit(emptyPreferences())
        else throw exception
    }.map { preferences ->
        MealAndDietType(
            preferences[PreferencesKeys.mealType] ?: Constant.DEFAULT_MEAL_TYPE,
            preferences[PreferencesKeys.mealTypeId] ?: 0,
            preferences[PreferencesKeys.dietType] ?: Constant.DEFAULT_DIET_TYPE,
            preferences[PreferencesKeys.dietTypeId] ?: 0
        )
    }
}

data class MealAndDietType(
    val mealType: String, val mealTypeId: Int, val dietType: String, val dietTypeId: Int,
)