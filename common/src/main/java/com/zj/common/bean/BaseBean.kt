package com.zj.common.bean

/**
 * createTime:2021/8/3 14:00
 * auth:张继
 * des:
 */
class BaseBean<T>(
    val errorMsg: String,
    val errorCode: Int,
    val data: T
)