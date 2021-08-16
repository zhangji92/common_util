package com.zj.common.expand

import android.content.res.Resources


fun Int.dp2px(): Float {
    val density = Resources.getSystem().displayMetrics.density
    return (density * this + 0.5f)
}

fun Int.px2dp(): Float {
    val density = Resources.getSystem().displayMetrics.density
    return (this / density + 0.5f)
}

fun Int.px2sp(): Float {
    val fontScale = Resources.getSystem().displayMetrics.scaledDensity
    return (this / fontScale + 0.5f)
}

fun Int.sp2px(): Float {
    val fontScale = Resources.getSystem().displayMetrics.scaledDensity
    return (this * fontScale + 0.5f)
}