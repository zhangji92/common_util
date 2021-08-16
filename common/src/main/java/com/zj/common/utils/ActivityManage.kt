package com.zj.common.utils

import android.app.Activity
import java.util.*

object ActivityManage {

    private val stack: Stack<Activity> = Stack()

    fun pushActivity(activity: Activity?) {
        stack.push(activity)
    }

    fun pop() {
        if (stack.isEmpty()) {
            return
        }
        stack.pop()
    }

    fun currentActivity(): Activity? {
        return if (stack.isEmpty()) {
            null
        } else stack.peek()
    }


    fun popAll() {
        for (activity in stack) {
            activity.finish()
        }
    }

}