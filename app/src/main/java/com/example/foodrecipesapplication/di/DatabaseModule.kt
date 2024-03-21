package com.example.foodrecipesapplication.di

import android.content.Context
import androidx.room.Room
import com.example.foodrecipesapplication.room.dao.RecipesDao
import com.example.foodrecipesapplication.room.database.RecipeDatabase
import com.example.foodrecipesapplication.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): RecipeDatabase =
        synchronized(context) {
            Room.databaseBuilder(
                context,
                RecipeDatabase::class.java,
                Constant.DATABASE_NAME
            ).allowMainThreadQueries().build()
        }

    @Provides
    @Singleton
    fun provideRecipeDao(database: RecipeDatabase): RecipesDao = database.recipesDao()
}