package com.zj.common.listener

interface SingleCallback {
    fun callback(
        isAllGranted: Boolean, granted: List<String?>,
        deniedForever: List<String?>, denied: List<String?>
    )
}