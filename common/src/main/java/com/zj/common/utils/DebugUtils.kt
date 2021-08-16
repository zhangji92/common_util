package com.zj.common.utils

import android.app.Activity
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import java.lang.Exception

object DebugUtils {
    const val LOG_TAG = "TAG----"

    var DEBUG_TAG = false

    fun enable() {
        DEBUG_TAG = true
    }

    fun disable() {
        DEBUG_TAG = false
    }

    fun getDebugMode(): Boolean {
        return DEBUG_TAG
    }

    fun printfLog(tag: String, log: String) {
        if (DEBUG_TAG) {
            if (!TextUtils.isEmpty(log)) Log.i(tag, log)
        }
    }

    fun printfLog(log: String) {
        printfLog(LOG_TAG, log)
    }

    fun printfWarning(tag: String, log: String) {
        if (DEBUG_TAG) {
            if (!TextUtils.isEmpty(log)) Log.w(tag, log)
        }
    }

    fun printfWarning(log: String) {
        printfWarning(LOG_TAG, log)
    }

    fun printfError(log: String) {
        if (DEBUG_TAG) {
            if (!TextUtils.isEmpty(log)) Log.e(LOG_TAG, log)
        }
    }

    fun printfError(Tag: String, log: String) {
        if (DEBUG_TAG) {
            if (!TextUtils.isEmpty(log)) Log.e(Tag, log)
        }
    }

    fun printfError(log: String, e: Exception) {
        if (DEBUG_TAG) {
            if (!TextUtils.isEmpty(log)) Log.e(LOG_TAG, log)
            e.printStackTrace()
        }
    }

    fun toast(activity: Activity, log: String) {
        if (DEBUG_TAG) {
            if (!TextUtils.isEmpty(log)) Toast.makeText(activity, log, Toast.LENGTH_SHORT).show()
        }
    }
}