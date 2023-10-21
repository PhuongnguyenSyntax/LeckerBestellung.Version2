package com.food.oder.data.flow

import android.util.Log
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Invocation
import retrofit2.Response
import java.lang.reflect.Type
import kotlin.coroutines.resume

class ResponseCallAdapter<T>(
    private val responseType: Type
) : CallAdapter<T, Flow<T>> {
    override fun adapt(call: Call<T>): Flow<T> {
        return flow {
            emit(
                suspendCancellableCoroutine { continuation ->
                    call.enqueue(FlowCallback(continuation))
                    continuation.invokeOnCancellation { call.cancel() }
                }
            )
        }
    }

    override fun responseType() = responseType
}

private class FlowCallback<T>(private val continuation: CancellableContinuation<T>) : Callback<T> {
    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful.not()) {

            return
        }
        val body = response.body()
        if (body == null) {

            return
        } else {
            continuation.resume(body)
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        Log.e("onFailure", "onFailure: $t", )
    }
}

class BodyCallAdapter<T>(private val responseType: Type) : CallAdapter<T, Flow<T>> {
    override fun adapt(call: Call<T>): Flow<T> {
        return flow {
            emit(
                suspendCancellableCoroutine { continuation ->
                    call.enqueue(FlowCallback(continuation))
                    continuation.invokeOnCancellation { call.cancel() }
                }
            )
        }
    }

    override fun responseType() = responseType


    fun Call<T>.createNullPointerError(): java.lang.NullPointerException {
        val invocation = this.request().tag(Invocation::class.java) ?: return NullPointerException()
        val method = invocation.method()
        return KotlinNullPointerException("Response from " + method.declaringClass.name + " - " + method.name)
    }
}