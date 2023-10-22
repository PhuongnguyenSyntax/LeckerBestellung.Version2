package com.food.oder.data.model

data class CartOrder(
    var id: String? = "",
    val fullName: String? = "",
    val numberPhone: String? = "",
    val address: String? = "",
    var menu: ArrayList<String>? = null,
    var date: String? = "",
    var totalAmount: Int? = 0
)
