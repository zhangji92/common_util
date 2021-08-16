package com.zj.common.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.jaeger.library.StatusBarUtil
import com.zj.common.listener.IBaseView
import com.zj.common.utils.ActivityManage.pop
import com.zj.common.utils.ActivityManage.pushActivity
import com.zj.common.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VM : BaseViewModel, VDB : ViewDataBinding> : AppCompatActivity(),
    IBaseView<VM> {
    protected val viewModel: VM by lazy {
        createViewModel()
    }
    protected lateinit var binding: VDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 添加活动到管理器
        pushActivity(this)
        // activity绑定布局文件
        binding = DataBindingUtil.setContentView(this, layoutId())
        if (isTranslucent) {
            // 隐藏状态栏
            StatusBarUtil.setTranslucentForImageView(this, 0, null)
            // 状态栏字体颜色黑色
            StatusBarUtil.setLightMode(this)
        }
        lifecycle.addObserver(viewModel)
        binding.setVariable(variableId(), viewModel)
        initData(savedInstanceState)

        viewModel.apply {
            activityEvent.observe(this@BaseActivity) {
                val activity = it["activity"] as Class<out Activity>
                val bundle = it["bundle"]
                goActivity(activity, bundle as Bundle?)
            }
        }


    }

    override fun createViewModel(): VM {
        return obtainViewModel(vMClass)
    }

    private val vMClass: Class<VM>
        private get() {
            var cls: Class<*>? = javaClass
            var vmClass: Class<VM>? = null
            while (vmClass == null && cls != null) {
                vmClass = getVMClass(cls) as Class<VM>?
                cls = cls.superclass
            }
            if (vmClass == null) {
                vmClass = BaseViewModel::class.java as Class<VM>
            }
            return vmClass
        }

    private fun getVMClass(cls: Class<*>): Class<*>? {
        val type = cls.genericSuperclass
        if (type is ParameterizedType) {
            val types = type.actualTypeArguments
            for (t in types) {
                if (t is Class<*>) {
                    val vmClass = t
                    if (BaseViewModel::class.java.isAssignableFrom(vmClass)) {
                        return vmClass
                    }
                } else if (t is ParameterizedType) {
                    val rawType = t.rawType
                    if (rawType is Class<*>) {
                        val vmClass = rawType
                        if (BaseViewModel::class.java.isAssignableFrom(vmClass)) {
                            return vmClass
                        }
                    }
                }
            }
        }
        return null
    }

    private fun obtainViewModel(tClass: Class<VM>): VM {
        return createViewModelProvider(this).get(tClass)
    }

    /**
     * 创建 [ViewModelProvider]
     *
     * @param owner ViewModelStoreOwner实例对象
     * @return 返回实例
     */
    private fun createViewModelProvider(owner: ViewModelStoreOwner): ViewModelProvider {
        return ViewModelProvider(
            owner,
            ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)
        )
    }

    override fun isTranslucent(): Boolean {
        return true
    }

    private fun goActivity(activity: Class<out Activity>, bundle: Bundle? = null) {
        val intent = Intent(this, activity)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    override fun onDestroy() {
        pop()
        binding.unbind()
        lifecycle.removeObserver(viewModel)
        super.onDestroy()
    }
}