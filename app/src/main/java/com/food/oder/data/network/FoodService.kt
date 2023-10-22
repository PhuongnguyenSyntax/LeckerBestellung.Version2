package com.food.oder.data.network

import com.food.oder.data.model.FoodCategory
import com.food.oder.data.model.RanDomFood
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodService {
    @GET("/api/json/v1/1/random.php")
    fun getRandomFood() : Flow<RanDomFood>

    @GET("/api/json/v1/1/filter.php?")
    fun getFoodCategory(
        @Query("a") country : String
    ) : Flow<FoodCategory>

    @GET("/api/json/v1/1/search.php?")
    fun searchFood(
        @Query("s") name : String
    ) : Flow<RanDomFood>

    @GET("/api/json/v1/1/lookup.php?")
    fun getFoodDetails(
        @Query("i") id : String
    ) : Flow<RanDomFood>
}