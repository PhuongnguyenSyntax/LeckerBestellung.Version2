package com.food.oder.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Table_cart")
data class Cart(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val urlImage : String,
    val name: String,
    val price : String,
    val amount : Int
)
