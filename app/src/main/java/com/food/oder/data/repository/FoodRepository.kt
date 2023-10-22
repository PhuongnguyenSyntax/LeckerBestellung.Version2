package com.food.oder.data.repository

import com.food.oder.data.network.FoodService
import javax.inject.Inject

class FoodRepository @Inject constructor(
    private val foodService: FoodService
) {
    fun getRanDomFood() = foodService.getRandomFood()
    fun getFoodCategory(country : String) = foodService.getFoodCategory(country)
    fun searchFood(name : String) = foodService.searchFood(name)
    fun getFoodDetails(id : String) = foodService.getFoodDetails(id)
}