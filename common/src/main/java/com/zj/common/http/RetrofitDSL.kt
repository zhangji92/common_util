package com.zj.common.http

import com.zj.common.bean.BaseBean

class RetrofitDSL<T> {

    lateinit var api: (suspend () -> BaseBean<T>)
    var onSuccess: ((BaseBean<T>) -> Unit)? = null
    var onFailed: ((msg: String, code: Int) -> Unit)? = null
    var onComplete: (() -> Unit)? = null

    /**
     * 获取数据
     * @param block (T) -> Unit
     */
    fun api(block: suspend () -> BaseBean<T>) {
        this.api = block
    }
    /**
     * 获取数据成功
     * @param block (T) -> Unit
     */
    fun onSuccess(block: (BaseBean<T>) -> Unit) {
        this.onSuccess = block
    }
    /**
     * 获取数据失败
     * @param block (msg: String, errorCode: Int) -> Unit
     */
    fun onFailed(block: (msg: String, code: Int) -> Unit) {
        this.onFailed = block
    }

    /**
     * 访问完成
     * @param block () -> Unit
     */
    fun onComplete(block: () -> Unit) {
        this.onComplete = block
    }


}