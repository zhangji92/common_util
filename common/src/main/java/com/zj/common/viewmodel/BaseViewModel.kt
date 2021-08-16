package com.zj.common.viewmodel

import android.app.Application
import com.zj.common.listener.ILifecycleObserver
import com.zj.common.livedata.SingleLiveEvent
import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import com.zj.common.http.HttpClient
import java.util.HashMap

open class BaseViewModel(application: Application) : AndroidViewModel(application),
    ILifecycleObserver {
    val activityEvent = SingleLiveEvent<Map<String, Any>>()
    override fun onCreate() {}
    override fun onStart() {}
    override fun onResume() {}
    override fun onPause() {}
    override fun onStop() {}
    override fun onDestroy() {}


    protected fun goActivity(activity: Class<out Activity>, bundle: Bundle? = null) {
        val map: MutableMap<String, Any> = HashMap()
        map["activity"] = activity
        if (bundle != null) {
            map["bundle"] = bundle
        }
        activityEvent.value = map
    }
}