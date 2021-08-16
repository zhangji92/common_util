package com.zj.android.activity

import android.os.Bundle
import android.view.View
import com.zj.android.BR
import com.zj.android.R
import com.zj.android.databinding.ActivityMainBinding
import com.zj.android.viewmodel.MainViewModel
import com.zj.common.activity.BaseActivity

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData(savedInstanceState: Bundle?) {


    }

    override fun variableId(): Int {
        return BR.main
    }

}