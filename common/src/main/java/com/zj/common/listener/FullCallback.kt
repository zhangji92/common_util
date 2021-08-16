package com.zj.common.listener

interface FullCallback {
    fun onGranted(granted: List<String?>)

    fun onDenied(deniedForever: List<String?>, denied: List<String?>)
}