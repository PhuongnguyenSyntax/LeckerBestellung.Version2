package com.food.oder.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import java.util.*

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        view.load(url)
    }
}