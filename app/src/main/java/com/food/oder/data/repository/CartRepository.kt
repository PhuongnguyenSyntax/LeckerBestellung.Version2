package com.food.oder.data.repository

import com.food.oder.data.database.FoodDataBase
import com.food.oder.data.model.Cart
import javax.inject.Inject

class CartRepository@Inject constructor(
    private val dataBase: FoodDataBase
) {
    fun getAllData() = dataBase.cartDao.getAllIData()
    fun getInsertOrUpdateData(cart : Cart) = dataBase.cartDao.insertOrUpdateData(cart)
    fun deleteData(id : Int) = dataBase.cartDao.deleteData(id)
    fun getDataWithPrice(price : String) = dataBase.cartDao.getDataWithPrice(price)
}