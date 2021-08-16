package com.zj.common.listener

import android.app.Activity

interface OnRationaleListener {
    fun rationale(activity: Activity, shouldRequest: ShouldRequest)

}