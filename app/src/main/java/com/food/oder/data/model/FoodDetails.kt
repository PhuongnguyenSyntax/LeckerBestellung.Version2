package com.food.oder.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


@Entity
data class FoodDetails(
    @PrimaryKey
    val id : Int = 0,
    val name: String
)

data class Food(
    @Json(name = "meals")
    var meals: List<Meal>
)

data class Meal(
    @Json(name = "idMeal")
    var idMeal: String,
    @Json(name = "strMeal")
    var strMeal: String,
    @Json(name = "strMealThumb")
    var strMealThumb: String
)