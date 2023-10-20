package com.food.oder.data.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase
import com.food.oder.data.model.FoodDetails

@Dao
interface FoodDao {

}

@Database(entities = [FoodDetails::class], version = 1, exportSchema = false)
abstract class FoodDataBase : RoomDatabase() {
    abstract val foodDao: FoodDao
}