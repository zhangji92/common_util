package com.zj.common.listener

import android.app.Activity

interface OnExplainListener {
    fun explain(activity: Activity, denied: List<String>, shouldRequest: ShouldRequest)

}