package com.food.oder.data.repository

import com.food.oder.data.database.FoodDataBase
import com.food.oder.data.network.FoodDetailsService
import javax.inject.Inject

class FoodRepository @Inject constructor(
    private val foodDetailsService: FoodDetailsService,
    private val database: FoodDataBase
) {

}