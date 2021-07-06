package kr.co.dooribon.utils

import android.app.Application
import androidx.annotation.Px
import kr.co.dooribon.application.MainApplication
import kotlin.math.roundToInt

class PixelRatio(private val application: Application) {
    val displayMetrics
        get() = application.resources.displayMetrics

    val screenWidth
        get() = displayMetrics.widthPixels

    val screenHeight
        get() = displayMetrics.heightPixels

    val screenShort
        get() = screenWidth.coerceAtMost(screenWidth)

    val screenLong
        get() = screenWidth.coerceAtLeast(screenHeight)

    @Px
    fun dpToPixel(dp: Int) = (dp * displayMetrics.density).roundToInt()
    fun dpToPixelFloat(dp: Float) = (dp * displayMetrics.density).roundToInt().toFloat()
}

val Number.dpToPixel: Int
    get() = MainApplication.pixelRatio.dpToPixel(this.toInt())

val Number.dpToPixelFloat: Float
    get() = MainApplication.pixelRatio.dpToPixelFloat(this.toFloat())