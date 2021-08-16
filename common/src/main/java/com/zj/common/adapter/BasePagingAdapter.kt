package com.zj.common.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.zj.common.holder.BaseViewHolder

abstract class BasePagingAdapter<T : Any, VDB : ViewDataBinding> :
    PagingDataAdapter<T, BaseViewHolder<VDB>>(object :
        DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: T,
            newItem: T
        ): Boolean {
            return oldItem == newItem
        }
    }) {
    override fun onBindViewHolder(holder: BaseViewHolder<VDB>, position: Int) {
        onBind(
            holder,
            position,
            DataBindingUtil.getBinding(holder.itemView),
            holder.itemView.context
        )
    }

    abstract fun onBind(holder: BaseViewHolder<VDB>, position: Int, binding: VDB?, context: Context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VDB> {
        return BaseViewHolder(
            onCreate(
                parent,
                viewType,
                parent.context,
                LayoutInflater.from(parent.context)
            )
        )
    }

    abstract fun onCreate(
        parent: ViewGroup,
        viewType: Int,
        context: Context,
        from: LayoutInflater
    ): VDB


}