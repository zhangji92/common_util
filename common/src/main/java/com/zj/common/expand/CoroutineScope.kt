package com.zj.common.expand

import com.zj.common.http.RetrofitDSL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


fun <T> CoroutineScope.retrofit(dsl: RetrofitDSL<T>.() -> Unit) {
    launch {
        val retrofitDSL = RetrofitDSL<T>()
        retrofitDSL.dsl()
        try {
            val result = retrofitDSL.api.invoke()
            when (result.errorCode) {
                0 -> {
                    retrofitDSL.onSuccess?.invoke(result)
                }
                else -> {
                    retrofitDSL.onFailed?.invoke(result.errorMsg, result.errorCode)
                }
            }
        } catch (e: Exception) {
            retrofitDSL.onFailed?.invoke(e.message.toString(), -1)
        } finally {
            retrofitDSL.onComplete?.invoke()
        }
    }

}