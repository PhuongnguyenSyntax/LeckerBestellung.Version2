package com.food.oder.data.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import com.food.oder.data.model.Cart
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateData(data: Cart)

    @Query("delete from Table_cart where id =:id")
    fun deleteData(id : Int)

    @Query("delete from Table_cart")
    fun deleteAllData()

    @Query("select * from Table_cart")
    fun getAllIData(): Flow<List<Cart>?>

    @Query("select * from Table_cart where price =:price")
    fun getDataWithPrice(price : String): Flow<Cart>
}

@Database(entities = [Cart::class], version = 1, exportSchema = false)
abstract class FoodDataBase : RoomDatabase() {
    abstract val cartDao: CartDao
}