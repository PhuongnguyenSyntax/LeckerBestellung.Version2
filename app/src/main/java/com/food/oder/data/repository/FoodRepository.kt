package com.food.oder.data.repository

import com.food.oder.data.database.FoodDataBase
import com.food.oder.data.network.FoodDetailsService
import com.food.oder.data.network.FoodService
import javax.inject.Inject

class FoodRepository @Inject constructor(
    private val foodService: FoodService,
    private val database: FoodDataBase
) {
    fun getRanDomFood() = foodService.getRandomFood()
    fun getFoodCategory(country : String) = foodService.getFoodCategory(country)
}