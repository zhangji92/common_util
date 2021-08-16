package com.zj.android

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zj.android.bean.ArticleItemBean
import com.zj.android.databinding.AdapterPagingBinding
import com.zj.common.adapter.BasePagingAdapter
import com.zj.common.holder.BaseViewHolder

class PagingAdapter : BasePagingAdapter<ArticleItemBean, AdapterPagingBinding>() {
    override fun onBind(
        holder: BaseViewHolder<AdapterPagingBinding>,
        position: Int,
        binding: AdapterPagingBinding?,
        context: Context
    ) {
        binding?.setVariable(BR.paging_item, getItem(position))
        binding?.executePendingBindings()
    }

    override fun onCreate(
        parent: ViewGroup,
        viewType: Int,
        context: Context,
        from: LayoutInflater
    ): AdapterPagingBinding {
        return AdapterPagingBinding.inflate(from, parent, false)
    }
}