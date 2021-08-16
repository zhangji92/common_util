package com.zj.android

import android.app.Application
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.zj.android.api.Api
import com.zj.common.http.HttpClient
import com.zj.common.utils.DebugUtils
import com.zj.common.view.MyRefreshFooter
import com.zj.common.view.MyRefreshHeader

class MyApplication : Application() {

    companion object {
        init {
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                layout.setPrimaryColorsId(R.color.purple_700, R.color.black) //全局设置主题颜色
                layout.setDragRate(0.4f)
                MyRefreshHeader(context)
            }
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ ->
                MyRefreshFooter(
                    context
                )
            }
        }
    }

    val api: Api by lazy {
        HttpClient.retrofit.create(Api::class.java)
    }

    override fun onCreate() {
        super.onCreate()
        DebugUtils.enable()
    }
}