package com.zj.common.utils

import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.util.Log
import java.lang.Exception
import java.lang.reflect.InvocationTargetException

object Utils {


    fun getApplicationByReflect(): Application? {
        try {
            val activityThreadClass = Class.forName("android.app.ActivityThread")
            val thread: Any? = getActivityThread()
            val app = activityThreadClass.getMethod("getApplication").invoke(thread) ?: return null
            return app as Application
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        return null
    }

    private fun getActivityThread(): Any? {
        val activityThread: Any? = getActivityThreadInActivityThreadStaticField()
        return activityThread ?: getActivityThreadInActivityThreadStaticMethod()
    }

    private fun getActivityThreadInActivityThreadStaticField(): Any? {
        return try {
            val activityThreadClass = Class.forName("android.app.ActivityThread")
            val sCurrentActivityThreadField =
                activityThreadClass.getDeclaredField("sCurrentActivityThread")
            sCurrentActivityThreadField.isAccessible = true
            sCurrentActivityThreadField[null]
        } catch (e: Exception) {
            DebugUtils.printfError("getActivityThreadInActivityThreadStaticField: ${e.message}")
            null
        }
    }

    private fun getActivityThreadInActivityThreadStaticMethod(): Any? {
        return try {
            val activityThreadClass = Class.forName("android.app.ActivityThread")
            activityThreadClass.getMethod("currentActivityThread").invoke(null)
        } catch (e: Exception) {
            DebugUtils.printfError("getActivityThreadInActivityThreadStaticMethod: ${e.message}")
            null
        }
    }

    fun isIntentAvailable(intent: Intent): Boolean {
        return getApplicationByReflect()?.packageManager?.queryIntentActivities(
            intent,
            PackageManager.MATCH_DEFAULT_ONLY
        )?.isNotEmpty() == true
    }


    fun getLaunchAppDetailsSettingsIntent(pkgName: String, isNewTask: Boolean): Intent? {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.parse("package:$pkgName")
        return getIntent(intent, isNewTask)
    }
    private fun getIntent(intent: Intent, isNewTask: Boolean): Intent? {
        return if (isNewTask) intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) else intent
    }
}