package kr.co.dooribon.utils.extension

import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import kr.co.dooribon.R
import kr.co.dooribon.utils.dpToPixel

fun Button.resizeMatchWidth() {
    val layoutParam = this.layoutParams
    layoutParam.width = context.resources.displayMetrics.widthPixels - context.dpToPixel(32)
    layoutParams = layoutParam
}

fun Button.resizeWrapWidth() {
    val layoutParam = this.layoutParams as ViewGroup.MarginLayoutParams
    layoutParam.width = (context.resources.displayMetrics.widthPixels / 2) - context.dpToPixel(16)
    layoutParams = layoutParam
}