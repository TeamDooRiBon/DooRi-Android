package kr.co.dooribon.utils

import android.content.Context
import kotlin.math.roundToInt

/**
 * Created by SSong-develop 2021-06-29
 *
 * Just use For CustomView dp , Not Whole Projects
 */
fun Context.dpToPixel(dp: Int): Int = (dp * resources.displayMetrics.density).roundToInt()

fun Context.dpToPixelFloat(dp: Int): Float =
    (dp * resources.displayMetrics.density).roundToInt().toFloat()