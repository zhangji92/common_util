package com.zj.android.activity

import android.os.Bundle
import com.zj.android.BR
import com.zj.android.R
import com.zj.android.databinding.ActivityLoginBinding
import com.zj.android.viewmodel.LoginViewModel
import com.zj.common.activity.BaseActivity

class LoginActivity:BaseActivity<LoginViewModel,ActivityLoginBinding>() {
    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun variableId(): Int {
        return BR.login
    }
}