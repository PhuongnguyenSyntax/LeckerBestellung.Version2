package com.food.oder.data.flow

import com.food.oder.data.liveData.MutableStateLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

suspend fun <T> Flow<T>.collectAsSateLiveData(liveData: MutableStateLiveData<T>) {
    this.flowOn(Dispatchers.IO)
        .catch {
            liveData.postError(it.message)
        }
        .onStart {
            liveData.postLoading()
        }.collect {
            liveData.postSuccess(it)
        }
}
