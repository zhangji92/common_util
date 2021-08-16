package com.zj.common.expand

import android.app.Application
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

fun Application.connectivityManage(): ConnectivityManager {
    return this.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
}

fun Application.isConnected(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val capabilities: NetworkCapabilities = this.connectivityManage()
            .getNetworkCapabilities(this.connectivityManage().activeNetwork)!!
        capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    } else {
        this.connectivityManage().activeNetworkInfo!!.isConnected
    }
}

