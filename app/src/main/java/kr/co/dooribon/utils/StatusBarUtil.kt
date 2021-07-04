package kr.co.dooribon.utils

import android.app.Activity
import androidx.annotation.ColorInt

object StatusBarUtil {
    fun changeColor(activity: Activity, @ColorInt color: Int) {
        activity.window?.run {
            statusBarColor = color
        }
    }
}