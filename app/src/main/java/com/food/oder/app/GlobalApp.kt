package com.food.oder.app

import android.annotation.SuppressLint
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.*
import java.util.*

@HiltAndroidApp
class GlobalApp : MultiDexApplication() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: GlobalApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}