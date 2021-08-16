package com.zj.android.activity

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.zj.android.BR
import com.zj.android.PagingAdapter
import com.zj.android.R
import com.zj.android.databinding.ActivityPagingBinding
import com.zj.android.viewmodel.PagingViewModel
import com.zj.common.activity.BaseActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PagingActivity : BaseActivity<PagingViewModel, ActivityPagingBinding>() {
    override fun layoutId(): Int {
        return R.layout.activity_paging
    }

    override fun initData(savedInstanceState: Bundle?) {

        val pagingAdapter = PagingAdapter()
        binding.apply {
            paging.adapter = pagingAdapter
        }

        viewModel.apply {
            viewModelScope.launch {
                data.collectLatest {
                    pagingAdapter.submitData(it)
                }
            }
        }

    }

    override fun variableId(): Int {
        return BR.paging
    }
}