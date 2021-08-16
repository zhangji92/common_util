package com.zj.android.viewmodel

import android.app.Application
import android.view.View
import androidx.annotation.NonNull
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingSource
import com.zj.android.MyApplication
import com.zj.android.activity.PagingActivity
import com.zj.android.bean.ArticleBean
import com.zj.android.bean.ArticleItemBean
import com.zj.common.bean.BaseBean
import com.zj.common.expand.retrofit
import com.zj.common.utils.DebugUtils
import com.zj.common.utils.SimplePager
import com.zj.common.viewmodel.BaseViewModel

class MainViewModel(@NonNull application: Application) : BaseViewModel(application) {

    val main = View.OnClickListener {

        //viewModelScope.retrofit<ArticleBean> {
        //    api { getApplication<MyApplication>().api.getArticle(1) }
        //    onSuccess {
        //        DebugUtils.printfError("请求成功")
        //    }
        //    onFailed { _, _ ->
        //        DebugUtils.printfError("请求失败")
        //    }
        //    onComplete {
        //        DebugUtils.printfError("请求完成")
        //    }
        //}
        //
        //
        goActivity(PagingActivity::class.java)
        ////PermissionUtils.permission(PermissionConstants.STORAGE)
        ////    .rationale(object : OnRationaleListener {
        ////        override fun rationale(activity: Activity, shouldRequest: ShouldRequest) {
        ////            shouldRequest.again(true)
        ////        }
        ////    }).callback(object : SimpleCallback {
        ////        override fun onGranted() {
        ////            Toast.makeText(getApplication(), "同意", Toast.LENGTH_SHORT).show()
        ////        }
        ////
        ////        override fun onDenied() {
        ////            Toast.makeText(getApplication(), "拒绝", Toast.LENGTH_SHORT).show()
        ////        }
        ////
        ////    }).theme(object :ThemeCallback{
        ////        override fun onActivityCreate(activity: Activity) {
        ////            activity.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        ////        }
        ////
        ////    }).request()


    }


    private val simplePager = object : SimplePager<Int, ArticleItemBean>() {
        override suspend fun loadData(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, ArticleItemBean> {
            val currentPage = params.key ?: 1
            return try {
                val data = getApplication<MyApplication>().api.getArticle(currentPage)
                PagingSource.LoadResult.Page(data.data.datas, null, 1)
            } catch (e: Exception) {
                PagingSource.LoadResult.Error(e)
            }
        }
    }

    val data = simplePager.getData(viewModelScope)

}