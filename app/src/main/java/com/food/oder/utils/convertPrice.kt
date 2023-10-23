package com.food.oder.utils

import java.text.NumberFormat
import java.util.Locale

fun convertPrice(number: String): String {
    return NumberFormat.getNumberInstance(Locale.US).format(number.toInt())
}