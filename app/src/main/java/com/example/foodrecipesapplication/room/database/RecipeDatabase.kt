package com.example.foodrecipesapplication.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodrecipesapplication.room.converters.RecipesTypeConverter
import com.example.foodrecipesapplication.room.dao.RecipesDao
import com.example.foodrecipesapplication.room.entities.FoodRecipeEntity
import com.example.foodrecipesapplication.utils.Constant
import java.io.Serializable
import kotlin.concurrent.Volatile

@Database(entities = [FoodRecipeEntity::class], version = 1, exportSchema = false)
@TypeConverters(RecipesTypeConverter::class)
abstract class RecipeDatabase : RoomDatabase(), Serializable {
    abstract fun recipesDao(): RecipesDao

    companion object {
        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        operator fun invoke(context: Context) = INSTANCE ?: getDatabase(context)

        private fun getDatabase(context: Context): RecipeDatabase {
            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    RecipeDatabase::class.java,
                    Constant.DATABASE_NAME
                ).allowMainThreadQueries().build()

                return INSTANCE as RecipeDatabase
            }
        }
    }
}