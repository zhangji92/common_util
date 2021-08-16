package com.zj.android.viewmodel

import android.app.Application
import android.view.View
import androidx.annotation.NonNull
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingSource
import com.zj.android.MyApplication
import com.zj.android.bean.ArticleBean
import com.zj.android.bean.ArticleItemBean
import com.zj.common.bean.BaseBean
import com.zj.common.expand.retrofit
import com.zj.common.utils.DebugUtils
import com.zj.common.utils.SimplePager
import com.zj.common.viewmodel.BaseViewModel

class PagingViewModel(@NonNull application: Application) : BaseViewModel(application) {

    private val simplePager = object : SimplePager<Int, ArticleItemBean>() {
        override suspend fun loadData(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, ArticleItemBean> {
            val currentPage = params.key ?: 1

            return try {
                val data = getApplication<MyApplication>().api.getArticle(currentPage)
                //当前页码 小于 总页码 页面加1
                val nextPage = if (currentPage < data.data.curPage) {
                    currentPage + 1
                } else {
                    //没有更多数据
                    null
                }
                PagingSource.LoadResult.Page(data.data.datas, null, nextPage)
            } catch (e: Exception) {
                PagingSource.LoadResult.Error(e)
            }
        }
    }

    val data = simplePager.getData(viewModelScope)

}