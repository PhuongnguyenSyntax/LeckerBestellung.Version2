package com.food.oder.data.network

import retrofit2.http.GET

interface FoodService {
    @GET("/aaa")
    fun getRandomFood()
}