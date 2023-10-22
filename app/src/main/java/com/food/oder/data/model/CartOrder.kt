package com.food.oder.data.model

data class CartOrder(
    val fullName : String,
    val numberPhone: String,
    val address : String,
    var menu : ArrayList<String>,
    var date : String,
    var totalAmount : Int
)
