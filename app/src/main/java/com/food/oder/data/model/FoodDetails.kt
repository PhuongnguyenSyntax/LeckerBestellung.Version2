package com.food.oder.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FoodDetails(
    @PrimaryKey
    val id : Int = 0,
    val name: String
)