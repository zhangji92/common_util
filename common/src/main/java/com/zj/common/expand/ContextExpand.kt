package com.zj.common.expand

import android.content.Context
import androidx.core.content.ContextCompat


fun Context.statusBarHeight(): Int {
    val identifier = resources.getIdentifier("status_bar_height", "dimen", "android")
    return resources.getDimensionPixelSize(identifier)
}



