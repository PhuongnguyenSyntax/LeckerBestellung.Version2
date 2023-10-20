package com.food.oder.di

import android.content.Context
import androidx.room.Room
import com.food.oder.data.database.FoodDao
import com.food.oder.data.database.FoodDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): FoodDataBase {
        return Room.databaseBuilder(
            appContext,
            FoodDataBase::class.java,
            "Food"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideChannelDao(foodDataBase: FoodDataBase): FoodDao {
        return foodDataBase.foodDao
    }
}