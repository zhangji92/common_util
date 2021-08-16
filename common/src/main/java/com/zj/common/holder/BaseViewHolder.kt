package com.zj.common.holder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * createTime:2021/8/3 10:25
 * auth:张继
 * des:
 */
class BaseViewHolder<VDB:ViewDataBinding>(item: VDB) : RecyclerView.ViewHolder(item.root) {
}